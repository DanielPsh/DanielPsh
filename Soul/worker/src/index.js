// Soul AI 렌즈 (v2.1) 라벨 인식 Worker.
//
// 이 Worker의 유일한 역할은 비전 LLM API 키를 클라이언트에 노출하지 않고
// 대신 호출해주는 것이다. 이미지를 저장하지 않고, 인식 결과(JSON)만
// 그대로 돌려준다. 배포 방법은 Soul/worker/README.md 참고.

const SYSTEM_PROMPT = `당신은 술병 라벨 사진에서 브랜드명과 제품명만 추출하는 도구입니다.
반드시 JSON 객체 하나만 응답하세요: {"brand": string|null, "name": string|null}
라벨을 읽을 수 없거나 사진에 술병이 없으면 둘 다 null로 응답하세요.
설명, 인사말, 코드블록 등 JSON 이외의 텍스트는 절대 포함하지 마세요.`;

// IP별 요청 횟수를 세는 Durable Object.
// Cloudflare KV는 무료 티어 쓰기가 하루 1,000회뿐이라 요청마다 카운터를
// 쓰면 실제 요청 한도보다 먼저 바닥난다 — 그래서 KV 대신 Durable Object의
// 인메모리 상태를 쓴다. (Durable Object 자체의 요청 과금/한도가 Worker의
// 하루 10만 요청과 별개로 집계되는지는 README의 "열린 질문"대로 아직
// 미확인 — 트래픽이 커지면 재확인 필요.)
export class RateLimiter {
  constructor(state) {
    this.state = state;
    this.count = 0;
    this.windowStart = Date.now();
  }

  async fetch() {
    const WINDOW_MS = 60_000; // 1분
    const MAX_REQUESTS = 5; // 분당 5회
    const now = Date.now();

    if (now - this.windowStart > WINDOW_MS) {
      this.windowStart = now;
      this.count = 0;
    }
    this.count += 1;

    const allowed = this.count <= MAX_REQUESTS;
    return new Response(JSON.stringify({ allowed }), {
      headers: { "content-type": "application/json" },
    });
  }
}

function corsHeaders(env) {
  return {
    "Access-Control-Allow-Origin": env.ALLOWED_ORIGIN || "*",
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    "Access-Control-Allow-Headers": "Content-Type",
  };
}

function json(data, init, env) {
  return new Response(JSON.stringify(data), {
    ...init,
    headers: { ...corsHeaders(env), "content-type": "application/json", ...(init && init.headers) },
  });
}

export default {
  async fetch(request, env) {
    if (request.method === "OPTIONS") {
      return new Response(null, { headers: corsHeaders(env) });
    }
    if (request.method !== "POST") {
      return json({ error: "POST만 허용됩니다." }, { status: 405 }, env);
    }

    const ip = request.headers.get("CF-Connecting-IP") || "unknown";
    const limiterId = env.RATE_LIMITER.idFromName(ip);
    const limiter = env.RATE_LIMITER.get(limiterId);
    const { allowed } = await (await limiter.fetch("https://internal/check")).json();

    if (!allowed) {
      return json(
        { error: "요청이 너무 잦아요. 잠시 후 다시 시도해주세요." },
        { status: 429 },
        env
      );
    }

    let body;
    try {
      body = await request.json();
    } catch {
      return json({ error: "잘못된 요청입니다." }, { status: 400 }, env);
    }

    const { imageBase64, mediaType } = body || {};
    if (!imageBase64 || !mediaType) {
      return json(
        { error: "imageBase64와 mediaType이 필요합니다." },
        { status: 400 },
        env
      );
    }

    try {
      const res = await fetch("https://api.anthropic.com/v1/messages", {
        method: "POST",
        headers: {
          "content-type": "application/json",
          "x-api-key": env.ANTHROPIC_API_KEY,
          "anthropic-version": "2023-06-01",
        },
        body: JSON.stringify({
          model: "claude-sonnet-5",
          max_tokens: 200,
          system: SYSTEM_PROMPT,
          messages: [
            {
              role: "user",
              content: [
                {
                  type: "image",
                  source: { type: "base64", media_type: mediaType, data: imageBase64 },
                },
                {
                  type: "text",
                  text: "이 사진 속 술병 라벨의 브랜드명과 제품명을 JSON으로 알려줘.",
                },
              ],
            },
          ],
        }),
      });

      if (!res.ok) {
        console.error("Anthropic API 오류:", await res.text());
        return json({ error: "인식 서비스 호출에 실패했어요." }, { status: 502 }, env);
      }

      const data = await res.json();
      const text = data.content?.[0]?.text ?? "{}";

      let parsed;
      try {
        parsed = JSON.parse(text);
      } catch {
        parsed = { brand: null, name: null };
      }

      return json(
        { brand: parsed.brand ?? null, name: parsed.name ?? null },
        {},
        env
      );
    } catch (err) {
      console.error("인식 처리 중 오류:", err);
      return json({ error: "인식 처리 중 오류가 발생했어요." }, { status: 500 }, env);
    }
  },
};

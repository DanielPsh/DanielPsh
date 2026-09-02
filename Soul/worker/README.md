# Soul AI 렌즈 Worker (v2.1)

라벨 사진을 Claude Vision API로 인식해주는 Cloudflare Worker. API 키를
프론트엔드에 노출하지 않기 위한 최소한의 백엔드다. 자세한 배경은
`../README.md`의 "AI 렌즈 기획 (v2)" 참고.

## 배포 전 준비물

1. [Cloudflare](https://dash.cloudflare.com/sign-up) 계정 (무료)
2. [Anthropic Console](https://console.anthropic.com/)에서 발급받은 API 키
3. Node.js (wrangler 실행용)

## 배포 방법

```bash
cd Soul/worker
npm install -g wrangler   # 이미 설치되어 있다면 생략
wrangler login            # 브라우저로 Cloudflare 계정 인증
wrangler secret put ANTHROPIC_API_KEY   # 프롬프트에 API 키 붙여넣기 (여기서만 입력, 코드에는 절대 쓰지 않음)
wrangler deploy
```

배포가 끝나면 `https://soul-ai-lens.<your-subdomain>.workers.dev` 같은
URL이 출력된다. 이 URL을 `Soul/scan.js`의 `LABEL_API` 상수에 넣어야
프론트엔드에서 실제로 호출된다.

배포 후 `wrangler.toml`의 `ALLOWED_ORIGIN`을 Soul이 실제로 호스팅되는
도메인으로 바꾸는 것을 권장한다 (기본값 `"*"`는 개발 중에만 쓰고, 아무
도메인에서나 이 Worker를 호출할 수 있게 방치하지 않기 위해).

## 로컬에서 테스트

```bash
wrangler dev
```

로컬 서버가 뜬 뒤, 다음처럼 직접 호출해서 응답을 확인할 수 있다:

```bash
curl -X POST http://localhost:8787 \
  -H "content-type: application/json" \
  -d '{"imageBase64": "<base64 문자열>", "mediaType": "image/jpeg"}'
```

## 참고

- 요청 남용 방지를 위해 IP당 분당 5회로 제한되어 있다 (`src/index.js`의
  `RateLimiter`). 필요하면 숫자를 조정한다.
- 이미지는 인식 목적으로만 Anthropic API에 전달되고 Worker에도 저장되지
  않는다.

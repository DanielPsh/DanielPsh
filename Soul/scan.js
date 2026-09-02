const OFF_PRODUCT_API = "https://world.openfoodfacts.org/api/v2/product";
const ZXING_CDN = "https://unpkg.com/@zxing/library@0.21.3/umd/index.min.js";

// 라벨 인식(v2.1)용 Cloudflare Worker 엔드포인트. 배포는 완료했지만
// Anthropic 계정에 크레딧이 없어 아직 호출이 실패한다 — 그래서 진입 버튼도
// main.html에서 hidden 처리해뒀다. 크레딧 충전 후 버튼의 hidden만 지우면
// 바로 동작한다 (Soul/worker/README.md 참고).
const LABEL_API = "https://soul-ai-lens.danielpark1030.workers.dev";

let activeStream = null;
let zxingReader = null;
let detectLoopId = null;

function supportsBarcodeDetector() {
  return "BarcodeDetector" in window;
}

function loadZXing() {
  if (window.ZXing) return Promise.resolve(window.ZXing);
  return new Promise((resolve, reject) => {
    const script = document.createElement("script");
    script.src = ZXING_CDN;
    script.onload = () => resolve(window.ZXing);
    script.onerror = () => reject(new Error("바코드 인식 라이브러리를 불러오지 못했어요"));
    document.head.appendChild(script);
  });
}

function stopCamera() {
  if (detectLoopId) cancelAnimationFrame(detectLoopId);
  detectLoopId = null;
  if (zxingReader) {
    zxingReader.reset();
    zxingReader = null;
  }
  if (activeStream) {
    activeStream.getTracks().forEach((track) => track.stop());
    activeStream = null;
  }
}

function closeScanOverlay() {
  stopCamera();
  document.getElementById("scanOverlay").classList.remove("open");
  document.getElementById("scanCapture").hidden = true;
  document.getElementById("scanPrivacyNote").hidden = true;
}

function setScanStatus(html) {
  document.getElementById("scanStatus").innerHTML = html;
}

function showScanRetry(message, retryFn = startScan) {
  setScanStatus(`${message}<br /><button id="scanRetry" class="fav-toggle">다시 스캔</button>`);
  document.getElementById("scanRetry").addEventListener("click", retryFn);
}

async function startScan() {
  document.getElementById("scanOverlay").classList.add("open");
  setScanStatus("카메라 준비 중...");

  const video = document.getElementById("scanVideo");

  try {
    if (supportsBarcodeDetector()) {
      await startNativeDetector(video);
    } else {
      await startZXingFallback(video);
    }
  } catch (err) {
    showScanRetry(`카메라를 사용할 수 없어요: ${err.message}`);
  }
}

async function startNativeDetector(video) {
  activeStream = await navigator.mediaDevices.getUserMedia({
    video: { facingMode: "environment" },
  });
  video.srcObject = activeStream;
  await video.play();

  const detector = new BarcodeDetector({
    formats: ["ean_13", "ean_8", "upc_a", "upc_e", "code_128"],
  });

  setScanStatus("바코드를 카메라에 비춰주세요");

  const loop = async () => {
    if (!activeStream) return;
    try {
      const codes = await detector.detect(video);
      if (codes.length > 0) {
        handleBarcode(codes[0].rawValue);
        return;
      }
    } catch {
      // 프레임이 아직 준비되지 않았을 때 발생할 수 있어 무시하고 재시도한다.
    }
    detectLoopId = requestAnimationFrame(loop);
  };
  loop();
}

async function startZXingFallback(video) {
  // iOS Safari는 BarcodeDetector를 지원하지 않아 ZXing으로 폴백한다.
  setScanStatus("바코드를 카메라에 비춰주세요 (호환 모드)");
  const ZXing = await loadZXing();
  zxingReader = new ZXing.BrowserMultiFormatReader();
  const devices = await zxingReader.listVideoInputDevices();
  const backCamera = devices[devices.length - 1];

  await zxingReader.decodeFromVideoDevice(
    backCamera ? backCamera.deviceId : undefined,
    video,
    (result) => {
      if (result) handleBarcode(result.getText());
    }
  );
}

async function handleBarcode(code) {
  stopCamera();
  setScanStatus(`바코드 ${code} 조회 중...`);

  try {
    const res = await fetch(`${OFF_PRODUCT_API}/${code}.json`);
    const data = await res.json();

    if (data.status !== 1 || !data.product) {
      showScanRetry(`바코드 ${code}에 해당하는 제품을 찾지 못했어요.`);
      return;
    }

    const item = mapOffProduct(code, data.product);
    const existing = LIQUORS.find(
      (l) => l.name.toLowerCase() === item.name.toLowerCase()
    );

    closeScanOverlay();
    openModalWithItem(existing || item);
  } catch (err) {
    showScanRetry(`조회 중 오류가 발생했어요: ${err.message}`);
  }
}

async function startRawCamera(video) {
  activeStream = await navigator.mediaDevices.getUserMedia({
    video: { facingMode: "environment" },
  });
  video.srcObject = activeStream;
  await video.play();
}

async function startLabelScan() {
  document.getElementById("scanOverlay").classList.add("open");
  setScanStatus("카메라 준비 중...");

  const video = document.getElementById("scanVideo");

  try {
    await startRawCamera(video);
    setScanStatus("라벨이 잘 보이게 카메라를 대고 촬영해주세요");
    document.getElementById("scanCapture").hidden = false;
    document.getElementById("scanPrivacyNote").hidden = false;
  } catch (err) {
    showScanRetry(`카메라를 사용할 수 없어요: ${err.message}`, startLabelScan);
  }
}

function captureFrame(video) {
  const canvas = document.createElement("canvas");
  canvas.width = video.videoWidth;
  canvas.height = video.videoHeight;
  canvas.getContext("2d").drawImage(video, 0, 0);
  const dataUrl = canvas.toDataURL("image/jpeg", 0.85);
  const match = dataUrl.match(/^data:(.+);base64,(.*)$/);
  return { mediaType: match[1], base64: match[2] };
}

async function handleLabelCapture() {
  const video = document.getElementById("scanVideo");
  const { base64, mediaType } = captureFrame(video);

  stopCamera();
  document.getElementById("scanCapture").hidden = true;
  document.getElementById("scanPrivacyNote").hidden = true;
  setScanStatus("라벨 인식 중...");

  try {
    const res = await fetch(LABEL_API, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify({ imageBase64: base64, mediaType }),
    });

    if (!res.ok) {
      const errBody = await res.json().catch(() => ({}));
      showScanRetry(errBody.error || "인식에 실패했어요.", startLabelScan);
      return;
    }

    const { brand, name } = await res.json();
    if (!name && !brand) {
      showScanRetry("라벨을 인식하지 못했어요.", startLabelScan);
      return;
    }

    const recognizedName = [brand, name].filter(Boolean).join(" ").trim();
    const existing = LIQUORS.find(
      (l) =>
        l.name.toLowerCase().includes(recognizedName.toLowerCase()) ||
        recognizedName.toLowerCase().includes(l.name.toLowerCase())
    );

    closeScanOverlay();

    if (existing) {
      openModalWithItem(existing);
      return;
    }

    // v2.1 계획대로: DB에 없으면 인식된 이름 외 모든 필드는 "정보 없음"으로
    // 채운다. 비전 LLM이 다른 값을 지어내도 그대로 신뢰하지 않는다.
    openModalWithItem({
      id: `label-${Date.now()}`,
      name: recognizedName,
      type: "라벨 인식 (DB에 없음)",
      region: "정보 없음",
      taste: "정보 없음",
      price: "정보 없음",
      calories: "정보 없음",
    });
  } catch (err) {
    showScanRetry(`인식 요청 중 오류가 발생했어요: ${err.message}`, startLabelScan);
  }
}

function mapOffProduct(code, product) {
  const kcal = product.nutriments && product.nutriments["energy-kcal_100g"];
  return {
    id: `off-${code}`,
    name: product.product_name || product.brands || `바코드 ${code}`,
    type: "바코드 조회",
    region: product.brands || product.countries || "정보 없음",
    taste: product.categories || "정보 없음",
    price: "가격 정보 없음",
    calories: kcal ? `${kcal} kcal / 100g` : "정보 없음",
  };
}

document.getElementById("scanButton").addEventListener("click", startScan);
document.getElementById("labelScanButton").addEventListener("click", startLabelScan);
document.getElementById("scanCapture").addEventListener("click", handleLabelCapture);
document.getElementById("scanClose").addEventListener("click", closeScanOverlay);
document.getElementById("scanOverlay").addEventListener("click", (e) => {
  if (e.target.id === "scanOverlay") closeScanOverlay();
});

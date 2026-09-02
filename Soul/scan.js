const OFF_PRODUCT_API = "https://world.openfoodfacts.org/api/v2/product";
const ZXING_CDN = "https://unpkg.com/@zxing/library@0.21.3/umd/index.min.js";

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
}

function setScanStatus(html) {
  document.getElementById("scanStatus").innerHTML = html;
}

function showScanRetry(message) {
  setScanStatus(`${message}<br /><button id="scanRetry" class="fav-toggle">다시 스캔</button>`);
  document.getElementById("scanRetry").addEventListener("click", startScan);
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
document.getElementById("scanClose").addEventListener("click", closeScanOverlay);
document.getElementById("scanOverlay").addEventListener("click", (e) => {
  if (e.target.id === "scanOverlay") closeScanOverlay();
});

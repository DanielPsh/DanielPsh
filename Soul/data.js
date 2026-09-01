// 맥주는 PunkAPI(fetchBeers), 와인은 SampleAPIs(fetchWines), 칵테일은 TheCocktailDB
// (fetchCocktails)에서 실시간으로 불러온다. 위스키/사케/막걸리/약주/과일주는 마땅한
// 오픈 데이터가 없어 직접 작성했다. 모두 script.js에서 병합한다.
const CURATED_LIQUORS = [
  {
    id: "whiskey-glenfiddich",
    name: "Glenfiddich 12",
    type: "위스키",
    region: "스코틀랜드",
    taste: "부드럽고 배·꿀향",
    price: "₩55,000",
    calories: "231 kcal / 100ml",
  },
  {
    id: "sake-junmai",
    name: "Junmai Sake",
    type: "사케",
    region: "일본",
    taste: "깔끔하고 쌀향",
    price: "₩15,000",
    calories: "103 kcal / 100ml",
  },
  {
    id: "makgeolli-jangsu",
    name: "장수 막걸리",
    type: "막걸리",
    region: "대한민국",
    taste: "새콤달콤, 부드러움",
    price: "₩1,800",
    calories: "46 kcal / 100ml",
  },
  {
    id: "yakju-baekseju",
    name: "백세주",
    type: "약주",
    region: "대한민국",
    taste: "한약재향, 은은한 단맛",
    price: "₩8,500",
    calories: "95 kcal / 100ml",
  },
  {
    id: "fruit-maesilju",
    name: "매실주",
    type: "과일주",
    region: "대한민국",
    taste: "새콤달콤한 매실향",
    price: "₩12,000",
    calories: "90 kcal / 100ml",
  },
];

# 개개발

아이디어:

1. 맥주, 와인, 위스키,… 용 앱(참고: 누룩) app idea
    1. Name of the Company: **Soul (술)**
    2. Purpose of the idea: 입문자들을 위한 가이드북및 손쉬운 구매
    3. Global APP
    4. 웹 개발 및 앱 (HTML → 뼈대, CSS → 꾸미기, JavaScript → func 다루기)
        1. WEB
            1. 나의 정보 (고객들의 이메일및전번 db)
            2. 광고및 관련 업체 
            3. 주문 및 결제 (e-commerce stores 입점)
        2. APP
            1. 서치 
            2. 종류 (맥주, 칵테일, 와인, 사케, 위스키, 막걸리, 약주, 과일주) 및 디테일 (정보 MySQL, 주류회사 및 주류) → liquor.com 정보 참고
                1. 맛, 가격, 무슨 지역의 대표 주류, 칼로리
            3. AI 렌즈 → 내가 찾고 싶은 주류의 정보
    5. things to learn
        1. *Synchronization between application and website (Login page 했을시 앱과 웹 연동)
            1. 예시. 앱에서 내가 AI 렌즈를 통해 주류를 찾아서 fav에 저장 → web에서 이 data를 web쪽 fav에서도 동기화 되게끔
        2. 결제 system (purchasing) & Ad (google ad?, should find some related company for investment)

---

## 정리된 기획 (v1 MVP)

위 아이디어 노트를 기준으로 지금 단계에서 실제로 구현 가능한 범위로 좁힌 버전.

**제품**
Soul(술) — 주류 입문자를 위한 가이드. 종류별(맥주/와인/위스키/사케/막걸리/칵테일/약주/과일주)
검색·필터·상세 정보 열람, 즐겨찾기 기능을 제공하는 웹 페이지.

**범위 (v1에 포함)**
- 검색 (이름 기준) + 카테고리 필터
- 상세 정보 모달 (지역, 맛/재료, 가격, 칼로리)
- 즐겨찾기 (로그인 없이 브라우저 localStorage에 저장)

**범위 밖 (다음 단계로 미룸)**
- 이커머스 결제/입점, 광고 게재
- AI 렌즈를 통한 주류 인식
- 앱-웹 로그인 및 즐겨찾기 동기화
- 회원 DB (이메일/전번 수집)

이 항목들은 모두 인증, 결제 연동, 별도 백엔드/DB 같은 인프라가 필요해서
MVP 검증 이후 우선순위를 다시 정해서 진행한다.

**데이터 소싱**
당초 계획한 "liquor.com 정보 참고"는 그대로 가져오지 않았다. liquor.com은
테이스팅 노트·리뷰가 저작권으로 보호되는 편집 콘텐츠 사이트라, 이를 그대로
긁어 자체 DB로 쓰면 저작권/이용약관 위반 소지가 있기 때문. 대신:

| 종류 | 데이터 출처 | 비고 |
|------|------------|------|
| 칵테일 | [TheCocktailDB](https://www.thecocktaildb.com/) | 무료 공개 API, CORS 허용 |
| 맥주 | [PunkAPI](https://github.com/alxiw/punkapi) | BrewDog가 재사용 목적으로 공개한 DIY Dog 데이터의 커뮤니티 포크 |
| 와인 | [SampleAPIs Wines](https://sampleapis.com/api-list/wines) | 개발자용 공개 샘플 데이터셋 (이미지는 Vivino CDN 직링크라 미사용) |
| 위스키/사케/막걸리/약주/과일주 | 직접 작성 | 위 조건을 만족하는 오픈 데이터셋을 찾지 못함 |

**기술 스택**
정적 HTML/CSS/JS (원래 계획한 "HTML → 뼈대, CSS → 꾸미기, JS → 기능"을
그대로 따름). 별도 백엔드 없이 클라이언트에서 오픈 API를 직접 호출.

## Process

1. **범위 정의** — 원본 아이디어 노트의 기능을 전부 넣으면 범위가 너무 커서,
   결제/광고/AI 렌즈/앱-웹 동기화는 제외하고 검색·상세·즐겨찾기만 남긴 v1
   MVP로 좁혔다.
2. **뼈대 구현** — `main.html` / `style.css` / `script.js` / `data.js`로
   구성된 정적 페이지 작성. 로컬 서버로 직접 열어 검색, 필터, 상세 모달,
   즐겨찾기 토글을 하나씩 수동 테스트.
3. **디자인 반복** — 첫 버전(카드 그리드)이 너무 단순하다는 피드백을 받고,
   에디토리얼 매거진 스타일(세리프 헤드라인, 번호 매긴 리스트, 마스트헤드
   헤더)로 리디자인. 데스크톱/모바일 반응형 확인.
4. **데이터 소싱 재검토** — "liquor.com을 DB로 쓰자"는 요청에 대해, 편집
   콘텐츠를 그대로 복제하는 것의 저작권 리스크를 먼저 확인. liquor.com은
   카테고리 구조 참고용으로만 남기고, TheCocktailDB / PunkAPI / SampleAPIs
   같은 재사용이 명시적으로 허용된 오픈 API로 대체.
5. **API 연동** — 세 API 모두 CORS 허용 여부와 실제 응답 필드를 먼저
   확인한 뒤, 각각 우리 데이터 스키마(`id`, `name`, `type`, `region`,
   `taste`, `price`, `calories`)에 맞게 매핑하는 fetch 함수를 작성.
   API가 제공하지 않는 필드(가격, 칼로리 등)는 값을 지어내지 않고
   "정보 없음"으로 명시.
6. **출처 표기** — 각 API 이용 조건에 맞게 페이지 하단에 데이터 출처 링크
   3개를 명시.
7. **버전 관리** — 매 단계를 별도 커밋으로 분리(MVP 기본 기능 → 리디자인
   → 오픈 데이터 연동)하고, `feature/soul-mvp` 브랜치에서 PR로 병합.
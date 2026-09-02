const FAVORITES_KEY = "soul-favorites";
const COCKTAIL_API = "https://www.thecocktaildb.com/api/json/v1/1";
const BEER_API = "https://punkapi-alxiw.amvera.io/v3/beers";
const WINE_API = "https://api.sampleapis.com/wines/reds";

let LIQUORS = [...CURATED_LIQUORS];

const state = {
  search: "",
  type: "전체",
  favoritesOnly: false,
};

async function fetchCocktails() {
  try {
    const listRes = await fetch(`${COCKTAIL_API}/filter.php?c=Cocktail`);
    const listData = await listRes.json();
    const picks = (listData.drinks || []).slice(0, 8);

    const details = await Promise.all(
      picks.map((d) =>
        fetch(`${COCKTAIL_API}/lookup.php?i=${d.idDrink}`).then((r) => r.json())
      )
    );

    return details
      .map((d) => d.drinks && d.drinks[0])
      .filter(Boolean)
      .map((d) => {
        const ingredients = [];
        for (let i = 1; i <= 5; i++) {
          if (d[`strIngredient${i}`]) ingredients.push(d[`strIngredient${i}`]);
        }
        return {
          id: `cocktail-${d.idDrink}`,
          name: d.strDrink,
          type: "칵테일",
          region: d.strIBA ? d.strIBA.replace(/_/g, " ") : "클래식 레시피",
          taste: ingredients.join(", "),
          price: "레시피",
          calories: "재료 참고",
          image: d.strDrinkThumb,
          instructions: d.strInstructions,
        };
      });
  } catch (err) {
    console.error("칵테일 데이터를 불러오지 못했습니다:", err);
    return [];
  }
}

async function fetchBeers() {
  try {
    const res = await fetch(`${BEER_API}?page=1&per_page=10`);
    const beers = await res.json();
    return beers.slice(0, 8).map((b) => ({
      id: `beer-punk-${b.id}`,
      name: b.name,
      type: "맥주",
      region: "BrewDog · 스코틀랜드",
      taste: b.description,
      price: "가격 정보 없음",
      calories: `ABV ${b.abv}%`,
    }));
  } catch (err) {
    console.error("맥주 데이터를 불러오지 못했습니다:", err);
    return [];
  }
}

async function fetchWines() {
  try {
    const res = await fetch(WINE_API);
    const wines = await res.json();
    return wines.slice(0, 8).map((w) => ({
      id: `wine-sample-${w.id}`,
      name: w.wine,
      type: "와인",
      region: (w.location || "").replace(/\n·\n/g, ", "),
      taste: `${w.winery} · 평점 ${w.rating?.average ?? "-"} (${
        w.rating?.reviews ?? "-"
      })`,
      price: "가격 정보 없음",
      calories: "정보 없음",
    }));
  } catch (err) {
    console.error("와인 데이터를 불러오지 못했습니다:", err);
    return [];
  }
}

function getFavorites() {
  try {
    return JSON.parse(localStorage.getItem(FAVORITES_KEY)) || [];
  } catch {
    return [];
  }
}

function toggleFavorite(id) {
  const favorites = getFavorites();
  const next = favorites.includes(id)
    ? favorites.filter((f) => f !== id)
    : [...favorites, id];
  localStorage.setItem(FAVORITES_KEY, JSON.stringify(next));
  render();
}

function getFilteredLiquors() {
  const favorites = getFavorites();
  return LIQUORS.filter((item) => {
    const matchesSearch = item.name
      .toLowerCase()
      .includes(state.search.toLowerCase());
    const matchesType = state.type === "전체" || item.type === state.type;
    const matchesFavorite = !state.favoritesOnly || favorites.includes(item.id);
    return matchesSearch && matchesType && matchesFavorite;
  });
}

function renderFilters() {
  const types = ["전체", ...new Set(LIQUORS.map((item) => item.type))];
  const filters = document.getElementById("filters");
  filters.innerHTML = types
    .map(
      (type) =>
        `<button class="filter-chip ${
          state.type === type ? "active" : ""
        }" data-type="${type}">${type}</button>`
    )
    .join("");

  filters.querySelectorAll(".filter-chip").forEach((btn) => {
    btn.addEventListener("click", () => {
      state.type = btn.dataset.type;
      render();
    });
  });
}

function renderGrid() {
  const favorites = getFavorites();
  const items = getFilteredLiquors();
  const grid = document.getElementById("grid");

  if (items.length === 0) {
    grid.innerHTML = `<p class="empty-state">해당하는 술이 없어요.</p>`;
    return;
  }

  grid.innerHTML = items
    .map((item, i) => {
      const isFav = favorites.includes(item.id);
      return `
        <div class="card" data-id="${item.id}">
          <span class="index">${String(i + 1).padStart(2, "0")}</span>
          <div class="name-col">
            <span class="type-badge">${item.type}</span>
            <h3>${item.name}</h3>
          </div>
          <p class="region-col">${item.region}</p>
          <p class="price-col">${item.price}</p>
          <button class="fav-star ${isFav ? "active" : ""}" data-fav="${item.id}">
            ${isFav ? "★" : "☆"}
          </button>
        </div>
      `;
    })
    .join("");

  grid.querySelectorAll(".fav-star").forEach((btn) => {
    btn.addEventListener("click", (e) => {
      e.stopPropagation();
      toggleFavorite(btn.dataset.fav);
    });
  });

  grid.querySelectorAll(".card").forEach((card) => {
    card.addEventListener("click", () => openModal(card.dataset.id));
  });
}

function openModal(id) {
  const item = LIQUORS.find((l) => l.id === id);
  if (!item) return;
  openModalWithItem(item);
}

function openModalWithItem(item) {
  document.getElementById("modalBody").innerHTML = `
    ${item.image ? `<img class="modal-image" src="${item.image}" alt="${item.name}" />` : ""}
    <span class="type-badge">${item.type}</span>
    <h2>${item.name}</h2>
    <dl>
      <dt>지역</dt><dd>${item.region}</dd>
      <dt>맛/재료</dt><dd>${item.taste}</dd>
      <dt>가격</dt><dd>${item.price}</dd>
      <dt>칼로리</dt><dd>${item.calories}</dd>
    </dl>
    ${item.instructions ? `<p class="modal-instructions">${item.instructions}</p>` : ""}
  `;
  document.getElementById("modalOverlay").classList.add("open");
}

function closeModal() {
  document.getElementById("modalOverlay").classList.remove("open");
}

function render() {
  renderFilters();
  renderGrid();

  const favToggle = document.getElementById("favToggle");
  favToggle.classList.toggle("active", state.favoritesOnly);
  favToggle.textContent = state.favoritesOnly
    ? "전체 보기"
    : "즐겨찾기만 보기";
}

document.getElementById("searchInput").addEventListener("input", (e) => {
  state.search = e.target.value;
  render();
});

document.getElementById("favToggle").addEventListener("click", () => {
  state.favoritesOnly = !state.favoritesOnly;
  render();
});

document.getElementById("modalClose").addEventListener("click", closeModal);
document.getElementById("modalOverlay").addEventListener("click", (e) => {
  if (e.target.id === "modalOverlay") closeModal();
});

async function init() {
  render();
  const [cocktails, beers, wines] = await Promise.all([
    fetchCocktails(),
    fetchBeers(),
    fetchWines(),
  ]);
  LIQUORS = [...CURATED_LIQUORS, ...beers, ...wines, ...cocktails];
  render();
}

init();

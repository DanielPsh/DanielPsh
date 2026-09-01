const FAVORITES_KEY = "soul-favorites";

const state = {
  search: "",
  type: "전체",
  favoritesOnly: false,
};

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
    .map((item) => {
      const isFav = favorites.includes(item.id);
      return `
        <div class="card" data-id="${item.id}">
          <button class="fav-star ${isFav ? "active" : ""}" data-fav="${item.id}">
            ${isFav ? "★" : "☆"}
          </button>
          <span class="type-badge">${item.type}</span>
          <h3>${item.name}</h3>
          <p>${item.region}</p>
          <p>${item.price}</p>
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

  document.getElementById("modalBody").innerHTML = `
    <span class="type-badge">${item.type}</span>
    <h2>${item.name}</h2>
    <dl>
      <dt>지역</dt><dd>${item.region}</dd>
      <dt>맛</dt><dd>${item.taste}</dd>
      <dt>가격</dt><dd>${item.price}</dd>
      <dt>칼로리</dt><dd>${item.calories}</dd>
    </dl>
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
    ? "★ 전체 보기"
    : "☆ 즐겨찾기만 보기";
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

render();

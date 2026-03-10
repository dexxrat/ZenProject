import BackgroundFrame from "../Layout/Background/BackgroundFrame";
import "../App.css";
import "./CategoryPage.css";
import Header from "../Components/Header/Header";

import ProductCard from "../Components/ProductCard/ProductCard";

function CategoryPage() {
  return (
    <>
      <BackgroundFrame />
      <Header />

      <div className="content-wrapper">
        <h2 className="category-title">CATEGORY</h2>
        <h3 className="h3-title">Zara categories:</h3>
        <div className="grid-products">
          <ProductCard />
          <ProductCard />
          <ProductCard />
          <ProductCard />
          <ProductCard />
          <ProductCard />
          <ProductCard />
        </div>
      </div>
    </>
  );
}

export default CategoryPage;

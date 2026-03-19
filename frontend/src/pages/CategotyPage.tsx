import BackgroundFrame from "../Layout/Background/BackgroundFrame";
import "../App.css";
import "./CategoryPage.css";
import Header from "../Components/Header/Header";
import { useState, useEffect } from "react";
import ProductCard from "../Components/ProductCard/ProductCard";

import { getCategoryProducts } from "../services/productService";
import type { Product } from "../types/Product";

function CategoryPage() {
  const [cards, setCards] = useState<Product[]>([]);

  useEffect(() => {
    getCategoryProducts().then((categoryCards) => {
      setCards(categoryCards);
    });
  }, []);

  return (
    <>
      <BackgroundFrame />
      <Header />

      <div className="content-wrapper">
        <h2 className="category-title">CATEGORY</h2>
        <h3 className="h3-title">Zara categories:</h3>
        <div className="grid-products">
          {cards.map((category, index) => (
            <ProductCard
              key={index}
              text={category.category + " / " + category.subcategory}
              img={category.imgUrl}
            />
          ))}
        </div>
      </div>
    </>
  );
}

export default CategoryPage;

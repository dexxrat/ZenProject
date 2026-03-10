import BackgroundFrame from "../Layout/Background/BackgroundFrame";
import Header from "../Components/Header/Header";

// import Line from "./../assets/Line 1.svg";

import "../App.css";
import "./TasteBattle.css";
import ProductElement from "../Components/ProductElement/ProdeuctElement";

function TasteBattle() {
  return (
    <>
      <Header />
      <BackgroundFrame />
      <div className="content-wrapper">
        <h2 className="category-title">TASTE BATTLE</h2>
        <h3 className="h3-title">round of 16 | Category: Jeans</h3>

        <div className="cards-container-grid">
          <ProductElement />
          {/* <img src={Line} alt="category image" className="category-image" /> */}
          <ProductElement />
        </div>

        <div className="buttons-category"></div>
      </div>
    </>
  );
}

export default TasteBattle;

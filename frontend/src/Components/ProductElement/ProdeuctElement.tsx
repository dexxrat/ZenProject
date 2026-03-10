import JeansLogo from "../../assets/Rectangle 11.svg";

import "../../App.css";
import "../../pages/TasteBattle.css";

import "./ProductElement.css";

function ProductElement() {
  return (
    <>
      <div className="center-content-wrapper">
        <img src={JeansLogo} alt="category image" className="category-image" />
        <div className="bio-chose">
          <div className="in-line">
            <h2 className="title-h2-bioJeans">Classic straight leg</h2>
            <h2 className="h2-cost">159 pln</h2>
          </div>

          <h3>BAGGY</h3>
        </div>
      </div>
    </>
  );
}

export default ProductElement;

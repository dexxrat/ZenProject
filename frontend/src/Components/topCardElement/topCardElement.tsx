import ProductElement from "../ProductElement/ProdeuctElement";
import BlueButton from "../BlueButton/BlueButton";
import "../BlueButton/BlueButton";
import "../../App.css";
import "../../pages/TasteBattle.css";

function topCardElement() {
  return (
    <>
      <div className="container">
        <ProductElement />
        <a
          href="https://www.zara.com/by/ru/"
          target="_blank"
          rel="noopener noreferrer"
        >
          <BlueButton text="VIEW" />
        </a>
      </div>
    </>
  );
}

export default topCardElement;

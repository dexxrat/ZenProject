import { useNavigate } from "react-router-dom";
import BlueButton from "../../Components/BlueButton/BlueButton";
import StatisticButton from "../../Components/StatisticButton/StatisticButton";
import categoryImg from "../../assets/category.svg";

function ProductCard() {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/test");
  };

  return (
    <>
      <div className="center-content-wrapper">
        <img
          src={categoryImg}
          alt="category image"
          className="category-image"
        />
        <div className="buttons-category">
          <StatisticButton />
          <BlueButton text="START" onClick={handleClick} />
        </div>
      </div>
    </>
  );
}

export default ProductCard;

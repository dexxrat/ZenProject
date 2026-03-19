import { useNavigate } from "react-router-dom";
import BlueButton from "../../Components/BlueButton/BlueButton";
import StatisticButton from "../../Components/StatisticButton/StatisticButton";
import "./ProductCard.css"; // УБЕДИСЬ ЧТО ЭТОТ ИМПОРТ ЕСТЬ!

interface CategoryProps {
  text: string;
  img: string;
}

function ProductCard({ text, img }: CategoryProps) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/test");
  };

  return (
    <div className="product-card">
      <div className="center-content-wrapper">
        <img src={img} alt={text} className="category-image" />
        <p>{text}</p>
        <div className="buttons-category">
          <StatisticButton />
          <BlueButton text="START" onClick={handleClick} />
        </div>
      </div>
    </div>
  );
}

export default ProductCard;

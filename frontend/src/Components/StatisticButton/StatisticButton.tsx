import statisticButton from "../../assets/statisticsButton.svg";
import "./StatisticButton.css";
import { useNavigate } from "react-router-dom";

function StatisticButton() {
  const navigate = useNavigate();

  const topButton = () => {
    navigate("/top");
  };

  return (
    <div>
      <button className="statisticButton" onClick={topButton}>
        <img src={statisticButton} />
      </button>
    </div>
  );
}

export default StatisticButton;

import BackgroundFrame from "../Layout/Background/BackgroundFrame";
import Header from "../Components/Header/Header";

import Line from "./../assets/Line 1.svg";
import userVariant from "./../assets/userVAriant.svg";

import "../App.css";
import "./TopPage.css";

import TopCardElement from "../Components/topCardElement/topCardElement";

function TopPage() {
  return (
    <>
      <BackgroundFrame />
      <Header />

      <div className="content-wrapper">
        <h2 className="category-title">top</h2>
        <div className="in-one-line">
          <h3 className="h3-title">top from :</h3>
          <button className="userVariant">
            <img src={userVariant}></img>
          </button>
        </div>

        <img src={Line} alt="category image" className="category-image" />
        <h2 className="title-top">JEANS</h2>

        <TopCardElement />
        <TopCardElement />
        <TopCardElement />
        <TopCardElement />
      </div>
    </>
  );
}

export default TopPage;

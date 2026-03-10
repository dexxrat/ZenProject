import menuLogo from "../../assets/Menu.svg";
import zenLogo from "../../assets/Zen.svg";
import profileLogo from "../../assets/profile.svg";

import { useNavigate } from "react-router-dom";

import "./Header.css";

function Header() {
  const navigate = useNavigate();
  const homeClick = () => {
    navigate("/");
  };

  return (
    <>
      <div className="header">
        <img src={menuLogo} alt="Menu" className="menu-icon" />
        <img src={zenLogo} alt="ZEN" className="zen-logo" onClick={homeClick} />
        <img src={profileLogo} alt="Profile" className="profile-logo" />
      </div>
    </>
  );
}

export default Header;

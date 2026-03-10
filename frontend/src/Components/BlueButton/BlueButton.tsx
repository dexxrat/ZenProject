import "./BlueButton.css";

interface ButtonProps {
  text: string;
  icon?: string;
  onClick?: () => void;
}

function BlueButton({ text, icon, onClick }: ButtonProps) {
  return (
    <button className="blue-button" onClick={onClick}>
      {icon && <img src={icon} alt="" className="button-icon" />}
      <span>{text}</span>
    </button>
  );
}

export default BlueButton;

import React from "react";
import { useNavigate } from "react-router-dom";
import "../landingPage/landingPage.css";

const LandingPage = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/login");
  };

  return (
    <div className="landing-page">
      <button className="login-button" onClick={handleLoginClick}>
        Login
      </button>
      <div className="content">
        <h1>Welcome to Our Hospital Management System</h1>
        <p>
          Manage your appointments, access patient records, and streamline
          operations with ease.
        </p>
        <p>Efficient care, tailored for you!</p>
      </div>
    </div>
  );
};

export default LandingPage;

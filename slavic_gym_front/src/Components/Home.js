import React from "react";
import { useNavigate } from "react-router-dom";
import "../Css/Home.css";

const Home = () => {
    const navigate = useNavigate();

    const handleLogin = () => {
        navigate('/login');
    }

    const handleSignup = () => {
        navigate('/signup');
    }

    return (
        <div className="home-container">
            <button className="home-button" onClick={handleLogin}>Login</button>
            <button className="home-button" onClick={handleSignup}>Signup</button>
        </div>
    );
}

export default Home;

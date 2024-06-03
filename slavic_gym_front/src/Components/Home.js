import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();

    const handleLogin = () => {
        navigate('/login');
    }

    const handleSignup = () => {
        navigate('/signup');
    }

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '100vh',
            backgroundColor: '#f5f5f5'
        }}>
            <button style={{
                margin: '0 10px',
                padding: '10px 20px',
                fontSize: '18px',
                border: 'none',
                borderRadius: '5px',
                backgroundColor: '#007BFF',
                color: 'white',
                cursor: 'pointer'
            }} onClick={handleLogin}>Login</button>
            <button style={{
                margin: '0 10px',
                padding: '10px 20px',
                fontSize: '18px',
                border: 'none',
                borderRadius: '5px',
                backgroundColor: '#007BFF',
                color: 'white',
                cursor: 'pointer'
            }} onClick={handleSignup}>Signup</button>
        </div>
    );
}

export default Home;
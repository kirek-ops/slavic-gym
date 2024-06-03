import React from 'react';
import { Route, BrowserRouter as Router, Routes, Link } from 'react-router-dom';
import { useNavigate, useLocation } from "react-router-dom";

const Interface = () => {
    const location = useLocation();
    const id = location.state.id;
    const username = location.state.id;

    const navigate = useNavigate();

    const handleQRButtonClick = () => {
        navigate('/myqr', { state: { id: id, username: username } });
    }

    return (
        <div style={{textAlign: 'center',
                     marginTop: '50px'}}>
            <h1>Welcome to the Gym App</h1>
            <button style={{padding: '10px 20px',
                                fontSize: '16px'}} onClick={handleQRButtonClick}>
                    Get My QR Code
            </button>
        </div>
    );
}

export default Interface;
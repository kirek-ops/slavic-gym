import React, { useState, useEffect } from 'react';
import { Route, BrowserRouter as Router, Routes, Link } from 'react-router-dom';
import { useNavigate, useLocation } from "react-router-dom";
import axios from 'axios';

const Interface = () => {
    const location = useLocation();
    const id = location.state.id;
    const username = location.state.username;

    const navigate = useNavigate();

    const [gyms, setGyms] = useState([]);
    const [selectedGym, setSelectedGym] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/gyms/getopengyms')
            .then(response => {
                setGyms(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the gym data!', error);
            });
    }, []);

    const handleGymChange = (event) => {
        setSelectedGym(event.target.value);
    };

    const handleQRButtonClick = () => {
        if (selectedGym) {
            navigate('/myqr', { state: { id: id, username: username, gym: selectedGym } });
        } else {
            alert("Please select a gym before proceeding.");
        }
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Welcome to the Gym App</h1>

            <div style={{ marginBottom: '20px' }}>
                <label htmlFor="gym-select">Choose a gym:</label>
                <select id="gym-select" value={selectedGym} onChange={handleGymChange} style={{ marginLeft: '10px', padding: '5px' }}>
                    <option value="">Select a gym</option>
                    {gyms.map((gym, index) => (
                        <option key={index} value={gym.id}>{gym.name}</option>
                    ))}
                </select>
            </div>

            <button style={{ padding: '10px 20px', fontSize: '16px' }} onClick={handleQRButtonClick}>
                Get My QR Code
            </button>
        </div>
    );
}

export default Interface;

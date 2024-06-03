import './App.css';
import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Signup from './Components/Signup';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<h1>Home</h1>} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/visits" element={<h1>Visits</h1>} />
                <Route path="/goals" element={<h1>Goals</h1>} />
                <Route path="/availibleclasses" element={<h1>availibleclasses</h1>} />
                <Route path="/profile" element={<h1>Profile</h1>} />
            </Routes>
        </Router>
    );
}

export default App;

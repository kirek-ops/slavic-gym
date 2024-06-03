import './App.css';
import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Signup from './Components/Signup';
import QRgen from "./Components/QRgen";
import Interface from "./Components/Interface";
import Home from './Components/Home';
import Visits from './Components/Visits';
import Membership from './Components/MembershipShop';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/interface" element={<Interface />} />
                <Route path="/myqr" element={<QRgen />}/>
                <Route path="/visits" element={<Visits/>} />
                <Route path="/goals" element={<h1>Goals</h1>} />
                <Route path="/availibleclasses" element={<h1>availibleclasses</h1>} />
                <Route path="/profile" element={<h1>Profile</h1>} />
                <Route path="/membershipshop" element={<Membership />} />
            </Routes>
        </Router>
    );
}

export default App;

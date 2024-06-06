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
import ClassSubmition from './Components/ClassSubmition';
import ClassBooking from './Components/ClassBooking';
import Shop from "./Components/Shop";
import Cart from "./Components/Cart";

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
                <Route path="/membershipshop" element={<Membership />} />
                <Route path="/submit-classes" element={<ClassSubmition />} />
                <Route path="/book-class" element={<ClassBooking />} />
                <Route path="/shop" element={<Shop />} />
                <Route path="/cart" element={<Cart />} />
            </Routes>
        </Router>
    );
}

export default App;

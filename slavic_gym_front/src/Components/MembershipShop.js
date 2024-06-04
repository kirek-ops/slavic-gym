import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router-dom";
import axios from 'axios';
import '../Css/MembershipShop.css'; // Import CSS file

const MembershipShop = () => {
    const location = useLocation();
    const { id, email } = location.state;
    const [memberships, setMemberships] = useState([]);
    const [userMemberships, setUserMemberships] = useState([]);
    const [hasBeenTransaction, setHasBeenTransaction] = useState(0);

    const fetchData = async () => {
        try {
            // Fetch all memberships
            const allMembershipsResponse = await axios.post('http://localhost:8080/memberships/getall');
            if (Array.isArray(allMembershipsResponse.data)) {
                setMemberships(allMembershipsResponse.data);
            } else {
                console.error('API response is not an array:', allMembershipsResponse.data);
            }

            // Fetch user's active memberships
            const userMembershipsResponse = await axios.post('http://localhost:8080/memberships/getbyid', { id: id });
            if (Array.isArray(userMembershipsResponse.data)) {
                setUserMemberships(userMembershipsResponse.data);
            } else {
                console.error('API response is not an array:', userMembershipsResponse.data);
            }
        } catch (error) {
            console.error('Error fetching memberships:', error);
        }
    };

    useEffect(() => {
        fetchData();
    }, [id]);

    const handleBuy = async (membershipId) => {
        try {
            const response = await axios.post('http://localhost:8080/memberships/buy', {
                memberId: id,
                membershipId
            });
            if (response.data.success) {
                alert('Membership purchased successfully!');

                setHasBeenTransaction(hasBeenTransaction + 1);
                fetchData();

                // Refresh user's memberships after purchase
                const userMembershipsResponse = await axios.post('http://localhost:8080/memberships/getbyid', { id });
                if (Array.isArray(userMembershipsResponse.data)) {
                    setUserMemberships(userMembershipsResponse.data);
                } else {
                    console.error('API response is not an array:', userMembershipsResponse.data);
                }
            } else {
                alert('Failed to purchase membership.');
            }
        } catch (error) {
            console.error('Error purchasing membership:', error);
            alert('Error purchasing membership.');
        }
    };

    return (
        <div className="membership-shop-container">
            <h1 className="membership-shop-header">Memberships for {email}</h1>
            <h2 className="membership-shop-header">Your Active Memberships</h2>
            <ul className="membership-list">
                {userMemberships.map(membership => (
                    <li key={membership.id_membership} className="membership-item">
                        <div className="membership-details">
                            <p className="membership-name">Name: {membership.name}</p>
                            <p className="membership-active">Active: {membership.is_active ? "Yes" : "No"}</p>
                            <p className="membership-price">Price: ${membership.price}</p>
                            <p className="membership-duration">Duration: {membership.duration} days</p>
                        </div>
                    </li>
                ))}
            </ul>
            <h2 className="membership-shop-header">Available Memberships</h2>
            <ul className="membership-list">
                {memberships.map(membership => (
                    <li key={membership.id_membership} className="membership-item">
                        <div className="membership-details">
                            <p className="membership-name">Name: {membership.name}</p>
                            <p className="membership-active">Active: {membership.is_active ? "Yes" : "No"}</p>
                            <p className="membership-price">Price: ${membership.price}</p>
                            <p className="membership-duration">Duration: {membership.duration} days</p>
                        </div>
                        <button onClick={() => handleBuy(membership.id_membership)} className="buy-button">Buy</button>
                    </li>
                ))}
            </ul>

        </div>
    );
};

export default MembershipShop;

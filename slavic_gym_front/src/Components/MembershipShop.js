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
            const userMembershipsResponse = await axios.post('http://localhost:8080/memberships/getbyid', { id });
            if (Array.isArray(userMembershipsResponse.data)) {
                const userMemberships = userMembershipsResponse.data;
                const filteredMemberships = filterUserMemberships(userMemberships);
                setUserMemberships(filteredMemberships);
                console.log('User memberships:', filteredMemberships);
            } else {
                console.error('API response is not an array:', userMembershipsResponse.data);
            }
        } catch (error) {
            console.error('Error fetching memberships:', error);
        }
    };

    const filterUserMemberships = (memberships) => {
        const currentDate = new Date();
        const activeMemberships = memberships.filter(m => !isExpired(m));

        if (activeMemberships.length > 0) {
            return activeMemberships;
        } else {
            const expiredMemberships = memberships.filter(m => isExpired(m));
            const uniqueMemberships = new Map();

            expiredMemberships.forEach(membership => {
                if (!uniqueMemberships.has(membership.id_membership)) {
                    uniqueMemberships.set(membership.id_membership, membership);
                } else {
                    const existingMembership = uniqueMemberships.get(membership.id_membership);
                    const existingStartDate = new Date(existingMembership.start_date);
                    const currentStartDate = new Date(membership.start_date);
                    if (currentStartDate > existingStartDate) {
                        uniqueMemberships.set(membership.id_membership, membership);
                    }
                }
            });

            return Array.from(uniqueMemberships.values());
        }
    };


    const isExpired = (membership) => {
        const currentDate = new Date();
        const startDate = new Date(membership.start_date);
        const expirationDate = new Date(startDate.getTime() + membership.duration * 24 * 60 * 60 * 1000);
        return currentDate > expirationDate;
    };

    useEffect(() => {
        fetchData();
    }, [id, hasBeenTransaction]);

    const handleBuy = async (membershipId) => {
        try {
            const response = await axios.post('http://localhost:8080/memberships/buy', {
                memberId: id,
                membershipId
            });
            if (response.data.success) {
                alert('Membership purchased successfully!');
                setHasBeenTransaction(prev => prev + 1);
            } else {
                alert('Failed to purchase membership.');
            }
        } catch (error) {
            console.error('Error purchasing membership:', error);
            alert('Error purchasing membership.');
        }
    };

    const handleProlong = async (membershipId) => {
        try {
            const response = await axios.post('http://localhost:8080/memberships/prolong', {
                memberId: id,
                membershipId
            });
            if (response.data.success) {
                alert('Membership prolonged successfully!');
                setHasBeenTransaction(prev => prev + 1);
                fetchData();
            } else {
                alert('Failed to prolong membership.');
            }
        } catch (error) {
            console.error('Error prolonging membership:', error);
            alert('Error prolonging membership.');
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
                            <p className="membership-expiration">Expiration Date: {new Date(new Date(membership.start_date).getTime() + (membership.duration * 24 * 60 * 60 * 1000)).toLocaleDateString()}</p>
                        </div>
                        {membership.is_active && isExpired(membership) && (
                            <button onClick={() => handleProlong(membership.id_membership)} className="prolong-button">Prolong</button>
                        )}
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

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useLocation, useNavigate } from "react-router-dom";
import '../Css/Visits.css';

const Visits = () => {
    const [visits, setVisits] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();
    const id = location.state.id; // Assuming the ID is passed via route state

    useEffect(() => {
        const fetchVisits = async () => {
            try {
                // Use template literals correctly
                const response = await axios.get(`http://localhost:8080/visits/member/${id}`);
                console.log(response.data);
                setVisits(response.data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchVisits();
    }, [id]); // Adding id as a dependency ensures the effect runs when id changes

    const handleReturnClick = () => {
        navigate('/interface', { state: { id: id } });
    };

    return (
        <div className="visits-container">
            <h1 className="visits-header">Visits</h1>
            {visits.length === 0 ? (
                <p>No visits found</p>
            ) : (
                visits.map((visit) => (
                    <div className="visit" key={visit.id_visit}>
                        <h2>Club Visit</h2>
                        <p>{new Date(visit.visit_time).toLocaleString()}</p>
                    </div>
                ))
            )}
            <button className="return-button" onClick={handleReturnClick}>
                Return
            </button>
        </div>
    );
};

export default Visits;

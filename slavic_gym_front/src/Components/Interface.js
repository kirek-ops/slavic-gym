import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import axios from 'axios';
import '../Css/Interface.css';

const Interface = () => {
    const location = useLocation();
    const id = location.state.id;
    const email = location.state.email;

    const navigate = useNavigate();

    const [gyms, setGyms] = useState([]);
    const [selectedGym, setSelectedGym] = useState('');
    const [userLocation, setUserLocation] = useState(null);
    const [positions, setPositions] = useState(null);
    const [hasPosition, setHasPosition] = useState({});

    useEffect(() => {
        // Get user geolocation
        navigator.geolocation.getCurrentPosition(
            position => {
                setUserLocation({
                    latitude: position.coords.latitude,
                    longitude: position.coords.longitude
                });
            },
            error => {
                console.error('Error obtaining geolocation:', error);
            }
        );
    }, []);

    useEffect(() => {
        axios.get('http://localhost:8080/gyms/getopengyms')
            .then(response => {
                if (Array.isArray(response.data)) {
                    setGyms(response.data);
                } else {
                    console.error('API response is not an array:', response.data);
                }
            })
            .catch(error => {
                console.error('There was an error fetching the gym data!', error);
            });
    }, []);

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await axios.get(`http://localhost:8080/employees/${id}/get-positions`);
                console.log(response.data);
                setPositions(response.data);
            } catch (error) {
                console.log("Error while obtaining positions");
            }
        }
        fetchData();
    }, [id]);

    useEffect(() => {
        if (positions) {
          const newHasPositions = Object.entries(positions).reduce((acc, [key, value]) => {
            if (!acc[value]) {
              acc[value] = [];
            }
            acc[value].push(parseInt(key));
            return acc;
          }, {});
          setHasPosition(newHasPositions);
        } else {
          setHasPosition({});
        }
        console.log(hasPosition);
    }, [positions]);

    useEffect(() => {
        const fetchGymLocations = async () => {
            try {
                const updatedGyms = await Promise.all(gyms.map(async gym => {
                    const gym_location = await axios.get(`http://localhost:8080/gyms/${gym.id_gym}/get-location`);
                    const { lat: gym_lat, lng: gym_lng } = gym_location.data;
                    const distance = calculateDistance(userLocation.latitude, userLocation.longitude, gym_lat, gym_lng);
                    return {
                        ...gym,
                        distance: distance
                    };
                }));

                const sortedGyms = updatedGyms.sort((a, b) => a.distance - b.distance);
                setGyms(sortedGyms);
            } catch (error) {
                console.error('Error fetching gym locations:', error);
            }
        };

        const interval = setInterval(fetchGymLocations, 10000); // Fetch every 10 seconds

        return () => clearInterval(interval); // Cleanup function to clear the interval

    }, [userLocation, gyms]);


    const calculateDistance = (lat1, lon1, lat2, lon2) => {
        const toRad = (value) => (value * Math.PI) / 180;
        const R = 6371; // Radius of the Earth in km
        const dLat = toRad(lat2 - lat1);
        const dLon = toRad(lon2 - lon1);
        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        const distance = R * c; // Distance in km
        return distance;
    };


    const handleGymChange = (event) => {
        setSelectedGym(event.target.value);
    };

    const handleQRButtonClick = () => {
        if (selectedGym) {
            navigate('/myqr', { state: { id: id, email: email, gym: selectedGym } });
        } else {
            alert("Please select a gym before proceeding.");
        }
    };

    const handleMembershipShopClick = () => {
        navigate('/membershipshop', { state: { id: id, email: email } });
    }

    const handleVisitsButton = () => {
        navigate('/visits', { state: { id: id, email: email } });
    }

    const handleSubmitClass = () => {
        navigate('/submit-classes', { state: { id: id, hasPosition: hasPosition, gyms: gyms } });
    }

    return (
        <div className="interface-container">
            <h1 className="interface-header">Welcome to the Gym App</h1>

            <div className="select-container">
                <label htmlFor="gym-select" className="label">Choose a gym:</label>
                <select id="gym-select" className="select" value={selectedGym} onChange={handleGymChange}>
                    <option value="">Select a gym</option>
                    {Array.isArray(gyms) && gyms.map((gym, index) => (
                        <option key={index} value={gym.id_gym}>{gym.name} - {gym.distance ? `${gym.distance.toFixed(2)} km` : 'Unknown'}</option>
                    ))}
                </select>
            </div>

            <button className="button" onClick={handleQRButtonClick}>Get My QR Code</button>
            <button className="button" onClick={handleVisitsButton}>See my visits</button>
            <button className="button" onClick={handleMembershipShopClick}>My memberships</button>
            { hasPosition['Trainer'] || hasPosition['Manager'] ? (<button className="button" onClick={handleSubmitClass}>Submit class</button>) : null}
        </div>
    );
}

export default Interface;

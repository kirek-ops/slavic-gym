import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router-dom";
import axios from 'axios';
import '../Css/MembershipShop.css'; // Import CSS file

const ClassSubmition = () => {
  const location = useLocation();
  const { id, hasPosition, gyms } = location.state;

  const [formData, setFormData] = useState({
    day: '',
    time_from: '',
    time_till: '',
    name: '',
  });

  console.log(id, hasPosition, gyms);

  const [selectedGym, setSelectedGym] = useState('');

  const handleGymChange = (event) => {
    setSelectedGym(event.target.value);
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: value
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Form submitted:', formData);
  };

  const allowedGyms = gyms.filter(gym => {
    const trainerGyms = hasPosition['Trainer'] || [];
    const managerGyms = hasPosition['Manager'] || [];
    return trainerGyms.includes(gym.id_gym) || managerGyms.includes(gym.id_gym);
  });

  console.log(allowedGyms);

  return (
    <div>
      <div style={{ marginBottom: '20px' }}>
        <label htmlFor="gym-select" style={{ marginRight: '10px', fontSize: '18px' }}>Choose a gym:</label>
        <select id="gym-select" value={selectedGym} onChange={handleGymChange} style={{ padding: '10px', fontSize: '16px' }}>
          <option value="">Select a gym</option>
          {Array.isArray(allowedGyms) && allowedGyms.map((gym, index) => (
            <option key={index} value={gym.id_gym}>
              {gym.name}
            </option>
          ))}
        </select>
      </div>

      {selectedGym && (
        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: '10px' }}>
            <label htmlFor="day" style={{ marginRight: '10px', fontSize: '16px' }}>Day:</label>
            <input
              type="text"
              id="day"
              name="day"
              value={formData.day}
              onChange={handleInputChange}
              style={{ padding: '10px', fontSize: '16px' }}
            />
          </div>
          <div style={{ marginBottom: '10px' }}>
            <label htmlFor="time_from" style={{ marginRight: '10px', fontSize: '16px' }}>Time From:</label>
            <input
              type="time"
              id="time_from"
              name="time_from"
              value={formData.time_from}
              onChange={handleInputChange}
              style={{ padding: '10px', fontSize: '16px' }}
            />
          </div>
          <div style={{ marginBottom: '10px' }}>
            <label htmlFor="time_till" style={{ marginRight: '10px', fontSize: '16px' }}>Time Till:</label>
            <input
              type="time"
              id="time_till"
              name="time_till"
              value={formData.time_till}
              onChange={handleInputChange}
              style={{ padding: '10px', fontSize: '16px' }}
            />
          </div>
          <div style={{ marginBottom: '10px' }}>
            <label htmlFor="name" style={{ marginRight: '10px', fontSize: '16px' }}>Name:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              style={{ padding: '10px', fontSize: '16px' }}
            />
          </div>
          <div style={{ marginBottom: '10px' }}>
            <label htmlFor="capacity" style={{ marginRight: '10px', fontSize: '16px' }}>Capacity:</label>
            <input
              type="number"
              id="capacity"
              name="capacity"
              value={formData.capacity}
              onChange={handleInputChange}
              style={{ padding: '10px', fontSize: '16px' }}
            />
          </div>
          <button type="submit" style={{ padding: '10px', fontSize: '16px' }}>Submit</button>
        </form>
      )}
      {/* <pre>{JSON.stringify(hasPosition, null, 2)}</pre> For debugging purposes */}
    </div>
  );
};

export default ClassSubmition;

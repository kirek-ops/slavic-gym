import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router-dom";
import axios from 'axios';
import '../Css/ClassSubmition.css'; // Import CSS file
import '../Css/ClassesList.css';

const ClassSubmition = () => {
  const location = useLocation();
  const { id, hasPosition, gyms } = location.state;

  const [formData, setFormData] = useState({
    class_name: '',
    schedule: '',
    time_from: '',
    time_till: '',
    id_gym: 0,
    capacity: 0,
    id_instructor: 0
  });

  useEffect(() => {
    setFormData(prevFormData => ({
      ...prevFormData,
      id_instructor: parseInt(id)
    }))
  }, [id]);

  console.log(id, hasPosition, gyms);

  const [selectedGym, setSelectedGym] = useState('');

  const handleGymChange = (event) => {
    setSelectedGym(event.target.value);
    setFormData(prevFormData => ({
      ...prevFormData,
      id_gym: parseInt(event.target.value)
    }))
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    const newValue = name === 'capacity' ? parseInt(value) : value;
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: newValue
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    console.log('Trying to submit form: ', formData);

    const isFormValid = Object.values(formData).every(value => value !== '');
    if (!isFormValid) {
      alert('Please fill in all fields.');
      return;
    }

    const response = await axios.put(`http://localhost:8080/classes/${id}/submit-class`, formData);
    console.log(response);
    console.log('Form submitted:', formData);

    alert(response.data);
  };

  const allowedGyms = gyms.filter(gym => {
    const trainerGyms = hasPosition['Trainer'] || [];
    const managerGyms = hasPosition['Manager'] || [];
    return trainerGyms.includes(gym.id_gym) || managerGyms.includes(gym.id_gym);
  });

  console.log(allowedGyms);

  const [classes, setClasses] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      await axios.get(`http://localhost:8080/classes/${id}/get-classes-from-today`)
        .then(response => {
          setClasses(response.data);
        })
        .catch(error => {
          console.error('There was an error fetching the classes!', error);
        });
    }
    fetchData();
    console.log("Classes is: ", classes);

    const intervalId = setInterval(fetchData, 60000);
    return () => clearInterval(intervalId);
  }, [id]);

  return (
    <div className="interface-container">
    <div className="select-container">
        <label htmlFor="gym-select" className="label">Choose a gym:</label>
        <select id="gym-select" value={selectedGym} onChange={handleGymChange} className="select">
          <option value="">Select a gym</option>
          {Array.isArray(allowedGyms) && allowedGyms.map((gym, index) => (
            <option key={index} value={gym.id_gym}>
              {gym.name}
            </option>
          ))}
        </select>
    </div>

    {selectedGym && (
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="day" className="label">Day:</label>
            <input
              type="text"
              id="day"
              name="schedule"
              value={formData.schedule}
              onChange={handleInputChange}
              className="input"
            />
          </div>
          <div className="form-group">
            <label htmlFor="time_from" className="label">Time From:</label>
            <input
              type="time"
              id="time_from"
              name="time_from"
              value={formData.time_from}
              onChange={handleInputChange}
              className="input"
            />
          </div>
          <div className="form-group">
            <label htmlFor="time_till" className="label">Time Till:</label>
            <input
              type="time"
              id="time_till"
              name="time_till"
              value={formData.time_till}
              onChange={handleInputChange}
              className="input"
            />
          </div>
          <div className="form-group">
            <label htmlFor="name" className="label">Name:</label>
            <input
              type="text"
              id="name"
              name="class_name"
              value={formData.class_name}
              onChange={handleInputChange}
              className="input"
            />
          </div>
          <div className="form-group">
            <label htmlFor="capacity" className="label">Capacity:</label>
            <input
              type="number"
              id="capacity"
              name="capacity"
              value={formData.capacity}
              onChange={handleInputChange}
              className="input"
            />
          </div>
          <button type="submit" className="button">Submit</button>
        </form>
      </div>
    )}

    <div className="container">
      <div className="header">Classes</div>
      {classes.map(classe => (
        <div key={classe.id_class} className="card">
          <div className="card-title">{classe.class_name}</div>
          <div className="card-content">
            <div className="card-item"><strong>Schedule:</strong> {classe.schedule}</div>
            <div className="card-item"><strong>Time From:</strong> {classe.time_from}</div>
            <div className="card-item"><strong>Time Till:</strong> {classe.time_till}</div>
            <div className="card-item"><strong>Gym ID:</strong> {classe.id_gym}</div>
            <div className="card-item"><strong>Capacity:</strong> {classe.capacity}</div>
            <div className="card-item"><strong>Instructor ID:</strong> {classe.id_instructor}</div>
          </div>
          </div>
      ))}
    </div>
  </div>
  );
};

export default ClassSubmition;

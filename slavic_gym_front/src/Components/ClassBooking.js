import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import '../Css/ClassSubmition.css'; // Import CSS file
import '../Css/ClassesList.css';

const ClassBooking = () => {
  const location = useLocation();
  const navigate = useNavigate(); // Import useNavigate
  const { id, gyms } = location.state;

  console.log(id, gyms);

  const [selectedGym, setSelectedGym] = useState('');

  const handleGymChange = (event) => {
    setSelectedGym(event.target.value);
  };

  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');

  const handleStartTimeChange = (event) => {
    setStartTime(event.target.value);
  };

  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
  };

  const [availableClasses, setAvailableClasses] = useState([]);

  const parseDate = (date) => {
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    console.log(year, month, day);

    if (!year || !month || !day) {
      return null;
    }

    if (year) {
      year = String(year).padStart(4, '0');
    }
    if (month) {
      month = String(month).padStart(2, '0');
    }
    if (day) {
      day = String(day).padStart(2, '0');
    }

    return `${year}-${month}-${day}`;
  };

  const handleSubmit = async () => {
    const form = {
      id_gym: parseInt(selectedGym),
      time_from: parseDate(new Date(startTime)),
      time_till: parseDate(new Date(endTime))
    };

    console.log(form);
    console.log("Sending to backend");

    try {
      const response = await axios.post(`http://localhost:8080/booking/get-classes`, form);
      console.log("Response for classes: ", response.data);

      setAvailableClasses(response.data);
    } catch (error) {
      alert("Set the correct date");
      return;
    }
  };

  useEffect(() => {
    console.log(availableClasses);
  }, [availableClasses]);

  const handleBook = async (id_class) => {
    try {
      console.log(id_class, id);
      const data = { id_class: id_class };
      await axios.post(`http://localhost:8080/booking/book/${id}`, data);

      alert("Successfully booked the class");
    } catch (error) {
      console.log(error);
      alert(error.response.data);
      return;
    }
  };

  const handleReturnClick = () => {
    navigate('/interface', { state: { id: id } });
  };

  const ClassItem = ({ classInfo }) => {
    const handleClick = () => {
      handleBook(classInfo.id_class);
    };

    return (
        <div className="class-item">
          <div className="class-content">
            <div className="class-info">
              <h3 className="class-name">{classInfo.class_name}</h3>
              <p className="class-time">{classInfo.time_from} - {classInfo.time_till}</p>
              <div className="class-details">
                <p><strong>Trainer:</strong> {classInfo.trainer}</p>
                <p><strong>Day:</strong> {classInfo.schedule}</p>
                <p><strong>Currently enrolled:</strong> {classInfo.filled} / {classInfo.capacity}</p>
              </div>
            </div>
            <button className="book-button" onClick={handleClick}>Book</button>
          </div>
        </div>
    );
  };

  return (
      <div className="chooser">
        <div className="interface-container">
          <div className="select-container">
            <label htmlFor="gym-select" className="label">Choose a gym:</label>
            <select id="gym-select" value={selectedGym} onChange={handleGymChange} className="select">
              <option value="">Select a gym</option>
              {Array.isArray(gyms) && gyms.map((gym, index) => (
                  <option key={index} value={gym.id_gym}>
                    {gym.name}
                  </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="start-time" className="label">Start Time:</label>
            <input
                type="date"
                id="start-time"
                value={startTime}
                onChange={handleStartTimeChange}
                className="input"
            />
          </div>

          <div className="form-group">
            <label htmlFor="end-time" className="label">End Time:</label>
            <input
                type="date"
                id="end-time"
                value={endTime}
                onChange={handleEndTimeChange}
                className="input"
            />
          </div>

          <button className="button" onClick={handleSubmit}>Find classes</button>
        </div>

        <div className="interface-container">
          <div className="class-grid">
            {availableClasses && availableClasses.map((classInfo, index) => (
                <ClassItem key={index} classInfo={classInfo} />
            ))}
          </div>
        </div>

        <div className="return-button-container">
          <button className="return-button" onClick={handleReturnClick}>
            Return
          </button>
        </div>
      </div>
  );
};

export default ClassBooking;

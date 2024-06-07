import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import '../Css/ProgressLogger.css'; // Import CSS for ProgressLogger

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);

const ProgressLogger = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = location.state;

  const [exerciseType, setExerciseType] = useState('');
  const [timeExercises, setTimeExercises] = useState([]);
  const [repExercises, setRepExercises] = useState([]);
  const [selectedExercise, setSelectedExercise] = useState('');
  const [inputValue, setInputValue] = useState('');
  const [logs, setLogs] = useState({});
  const [selectedLogExercise, setSelectedLogExercise] = useState('');
  const [chartData, setChartData] = useState(null);

  useEffect(() => {
    const fetchExercises = async () => {
      try {
        const timeResponse = await axios.get(`http://localhost:8080/exercises/time-exercises`);
        const repResponse = await axios.get(`http://localhost:8080/exercises/rep-exercises`);
        setTimeExercises(timeResponse.data);
        setRepExercises(repResponse.data);
      } catch (error) {
        console.error('Error fetching exercises:', error);
      }
    };
    fetchExercises();

    const fetchLogs = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/logs/get-logs/${id}`);
        console.log(response.data);
        setLogs(response.data);
      } catch (error) {
        console.error('Error fetching logs:', error);
      }
    };
    fetchLogs();
  }, [id]);

  const handleTypeChange = (e) => {
    setExerciseType(e.target.value);
    setSelectedExercise('');
    setInputValue('');
  };

  const handleExerciseChange = (e) => {
    setSelectedExercise(e.target.value);
    setInputValue('');
  };

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      exerciseId: parseInt(selectedExercise),
      value: parseInt(inputValue),
      type: exerciseType
    };

    console.log(id, data);

    try {
      await axios.post(`http://localhost:8080/logs/submit-log/${id}`, data);
      alert('Exercise submitted successfully!');
    } catch (error) {
      alert('Error submitting exercise:', error);
    }
  };

  const handleReturnClick = () => {
    navigate('/interface', { state: { id: id } });
  };

  const handleLogExerciseChange = (e) => {
    setSelectedLogExercise(e.target.value);
    const selectedLogs = logs[e.target.value] || [];
    const labels = selectedLogs.map(log => log.log_date);
    const data = selectedLogs.map(log => log.done);

    setChartData({
      labels: labels,
      datasets: [
        {
          label: e.target.value,
          data: data,
          borderColor: 'rgba(75, 192, 192, 1)',
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
        },
      ],
    });
  };

  return (
      <div className="progress-logger-container">
        <form onSubmit={handleSubmit}>
          <div>
            <label>
              Choose Exercise Type:
              <select value={exerciseType} onChange={handleTypeChange}>
                <option value="">Select...</option>
                <option value="time">Time</option>
                <option value="repetition">Repetitions</option>
              </select>
            </label>
          </div>
          {exerciseType && (
              <div>
                <label>
                  Choose Exercise:
                  <select value={selectedExercise} onChange={handleExerciseChange}>
                    <option value="">Select...</option>
                    {(exerciseType === 'time' ? timeExercises : repExercises).map((exercise) => (
                        <option key={exercise.id_exercise} value={exercise.id_exercise}>
                          {exercise.exercise_name}
                        </option>
                    ))}
                  </select>
                </label>
              </div>
          )}
          {selectedExercise && (
              <div>
                <label>
                  {exerciseType === 'time' ? 'Time (minutes):' : 'Repetitions:'}
                  <input
                      type="number"
                      value={inputValue}
                      onChange={handleInputChange}
                      required
                  />
                </label>
              </div>
          )}
          {selectedExercise && (
              <div>
                <button type="submit">Submit</button>
              </div>
          )}
        </form>
        <div className="logs-section">
          <h2>Exercise Logs</h2>
          <label>
            Choose Exercise to View Progress:
            <select value={selectedLogExercise} onChange={handleLogExerciseChange}>
              <option value="">Select...</option>
              {Object.keys(logs).map((exerciseName, index) => (
                  <option key={index} value={exerciseName}>
                    {exerciseName}
                  </option>
              ))}
            </select>
          </label>
          {chartData && (
              <div className="chart-container">
                <Line data={chartData} />
              </div>
          )}
        </div>
        <div className="return-button-container">
          <button className="return-button" onClick={handleReturnClick}>
            Return
          </button>
        </div>
      </div>
  );
};

export default ProgressLogger;

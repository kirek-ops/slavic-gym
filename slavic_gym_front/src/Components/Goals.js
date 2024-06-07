import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import '../Css/ClassSubmition.css'; // Import CSS file
import '../Css/ClassesList.css';
import '../Css/Goals.css'; // Import CSS for Goals

const Goals = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = location.state;

  const [exerciseType, setExerciseType] = useState('');
  const [timeExercises, setTimeExercises] = useState([]);
  const [repExercises, setRepExercises] = useState([]);
  const [selectedExercise, setSelectedExercise] = useState('');
  const [inputValue, setInputValue] = useState('');
  const [goals, setGoals] = useState([]);
  const [selectedGoal, setSelectedGoal] = useState(null);
  const [uniqueGoals, setUniqueGoals] = useState([]);

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

    const fetchGoals = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/goals/get-goals/${id}`);
        setGoals(response.data);
        const uniqueGoalNamesArray = response.data.reduce((acc, current) => {
          if (!acc.includes(current.exercise_name)) {
            return acc.concat(current.exercise_name);
          } else {
            return acc;
          }
        }, []);
        setUniqueGoals(uniqueGoalNamesArray);
        console.log(uniqueGoalNamesArray);
      } catch (error) {
        console.error('Error fetching goals:', error);
      }
    };
    fetchGoals();
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

  const handleGoalChange = (e) => {
    const selectedGoals = goals.filter(goal => goal.exercise_name === e.target.value);
    setSelectedGoal(selectedGoals);
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
      await axios.post(`http://localhost:8080/goals/submit-goal/${id}`, data);
      alert('Goal submitted successfully! Good luck!');
    } catch (error) {
      alert('Error submitting exercise:', error);
    }
  };

  const handleReturnClick = () => {
    navigate('/interface', { state: { id: id } });
  };

  return (
      <div className="goals-container">
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
        <div className="goals-list">
          <h2>Goals List</h2>
          <label>
            Choose Goal to View Description:
            <select onChange={handleGoalChange}>
              <option value="">Select...</option>
              {uniqueGoals.map((goal, index) => (
                  <option key={index} value={goal}>
                    {goal}
                  </option>
              ))}
            </select>
          </label>
          {Array.isArray(selectedGoal) && selectedGoal.map((goal, index) => (
              <div className="goal-description" key={index}>
                <h3>{goal.exercise_name}</h3>
                <p>{goal.description}</p>
                <p>Target: {goal.target}</p>
                <p>Completion: {goal.completion ? goal.completion : 'Incomplete'}</p>
              </div>
          ))}
        </div>
        <div className="return-button-container">
          <button className="return-button" onClick={handleReturnClick}>
            Return
          </button>
        </div>
      </div>
  );
};

export default Goals;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import bcrypt from 'bcryptjs';
import { useNavigate } from 'react-router-dom';
import '../Css/Signup.css'; // Import the CSS file

// Function to validate email
const isValidEmail = (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
};

// Function to validate phone number
const isValidPhone = (phone) => {
    const re = /^[0-9]{10,15}$/;
    return re.test(String(phone));
};

const SignupPage = () => {
    const [firstName, setFirstName] = useState('');
    const [secondName, setSecondName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [id, setId] = useState('');
    const [positions, setPositions] = useState([]);
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const checkUserExists = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/checkUserExists', { email, phone });
            console.log(email, phone, response.data);
            return response.data.success;
        } catch (error) {
            console.error('Error checking user existence:', error);
            return false;
        }
    };

    const handleSubmit = async () => {
        const validationErrors = {};

        if (!isValidEmail(email)) {
            validationErrors.email = 'Invalid email address';
        }

        if (!isValidPhone(phone)) {
            validationErrors.phone = 'Invalid phone number';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        const userExists = await checkUserExists();
        if (userExists) {
            alert('User with this email or phone already exists');
            return;
        }

        const hashedPassword = await bcrypt.hash(password, await bcrypt.genSalt(10));
        try {
            const requestBody = { firstName, secondName, email, phone, password: hashedPassword };
            console.log('Request body:', requestBody);
            const response = await axios.post('http://localhost:8080/auth/signup', requestBody);
            console.log('Signup response:', response.data);
            setId(response.data.id);
            localStorage.setItem('email', email);
            navigate('/interface', { state: { id: response.data.id, email: email } });
        } catch (error) {
            console.error('Error signing up:', error);
            alert('Error signing up');
        }
    };

    useEffect(() => {
        if (id) {
            console.log('Updated id:', id);
            alert('Signup successful');
            navigate('/interface', { state: { id: id, email: email } });
        }
    }, [id, navigate]);

    return (
        <div className="signup-container">
            <label className="signup-label">First Name:</label>
            <input
                type="text"
                name="First Name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                className="signup-input"
            />
            <br/>

            <label className="signup-label">Second Name:</label>
            <input
                type="text"
                name="Second Name"
                value={secondName}
                onChange={(e) => setSecondName(e.target.value)}
                className="signup-input"
            />
            <br/>

            <label className="signup-label">Email:</label>
            <input
                type="text"
                name="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="signup-input"
            />
            {errors.email && <div className="signup-error">{errors.email}</div>}
            <br/>

            <label className="signup-label">Phone:</label>
            <input
                type="text"
                name="Phone"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                className="signup-input"
            />
            {errors.phone && <div className="signup-error">{errors.phone}</div>}
            <br/>

            <label className="signup-label">Password:</label>
            <input
                type="password"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="signup-input"
            />
            <br/>

            <button onClick={handleSubmit} className="signup-button">Sign Up</button>
        </div>
    );
};

export default SignupPage;

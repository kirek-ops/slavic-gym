import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import bcrypt from 'bcryptjs';
import '../Css/Login.css';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        // Clear local storage when the component mounts
        localStorage.clear();
    }, []);

    const handleLogin = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/auth/login?email=${email}`);
            if (response.data.success) {
                const { id, correctPassword } = response.data;
                const isPasswordCorrect = await bcrypt.compare(password, correctPassword);
                if (!isPasswordCorrect) {
                    alert('Incorrect email or password');
                    return;
                }
                alert('Login successful');
                localStorage.setItem('email', email);
                navigate('/interface', { state: { id: id, email: email } });
            } else {
                alert(response.data.message || 'Incorrect email or password');
            }
        } catch (error) {
            alert('Something went wrong on the server...');
            console.error(error);
        }
    };

    return (
        <div className="login-container">
            <label>Email:</label>
            <input type="text" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />

            <label>Password:</label>
            <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />

            <button className={"login-button"} onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;

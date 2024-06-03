import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import bcrypt from 'bcryptjs';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/auth/login?username=${username}`);
            if (response.data.success) {
                const { id, correctPassword } = response.data;
                if (!await bcrypt.compare(password, correctPassword)) {
                    alert('Incorrect login or password');
                    return;
                }
                alert('Login successful');
                navigate('/interface', { state: { id: id, username: username } });
            }
            else {
                alert('Incorrect login or password');
                return;
            }
        } catch (error) {
            alert('Something went wrong on server...');
            console.error(error);
        }
    }

    return (
        <div>
            <label>Username:</label>
            <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
            <br/>

            <label>Password:</label>
            <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <br/>

            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;
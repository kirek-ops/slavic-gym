import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import bcrypt from 'bcryptjs';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

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
                navigate('/interface', { state: { id: id, email: email } });
            } else {
                alert(response.data.message || 'Incorrect email or password');
            }
        } catch (error) {
            alert('Something went wrong on server...');
            console.error(error);
        }
    };

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
            <label style={{ marginBottom: '10px' }}>Email:</label>
            <input type="text" name="email" value={email} onChange={(e) => setEmail(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <label style={{ marginBottom: '10px' }}>Password:</label>
            <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <button onClick={handleLogin} style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#007BFF', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>Login</button>
        </div>
    );
}

export default Login;

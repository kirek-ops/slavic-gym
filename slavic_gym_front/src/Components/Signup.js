import React, { useEffect, useState } from 'react';
import axios from 'axios';
import bcrypt from 'bcryptjs';
import { useNavigate } from 'react-router-dom';

const SignupPage = () => {
    const [firstName, setFirstName] = useState('');
    const [secondName, setSecondName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [id, setId] = useState('');
    const navigate = useNavigate();

    const checkUserExists = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/checkUserExists', {
                email, phone
            });
            return !response.data.success;
        } catch (error) {
            console.error('Error checking user existence:', error);
            return false;
        }
    };

    const handleSubmit = async () => {
        const userExists = await checkUserExists();
        if (userExists) {
            alert('User with this email or phone already exists');
            return;
        }

        const hashedPassword = await bcrypt.hash(password, await bcrypt.genSalt(10));
        try {
            const requestBody = {
                firstName, secondName, email, phone, password: hashedPassword,
            };
            console.log('Request body:', requestBody);
            const response = await axios.post('http://localhost:8080/auth/signup', requestBody);
            console.log('Signup response:', response.data);
            setId(response.data.id);
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
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
            <label style={{ marginBottom: '10px' }}>First Name:</label>
            <input type="text" name="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <label style={{ marginBottom: '10px' }}>Second Name:</label>
            <input type="text" name="Second Name" value={secondName} onChange={(e) => setSecondName(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <label style={{ marginBottom: '10px' }}>Email:</label>
            <input type="text" name="Email" value={email} onChange={(e) => setEmail(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <label style={{ marginBottom: '10px' }}>Phone:</label>
            <input type="text" name="Phone" value={phone} onChange={(e) => setPhone(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <label style={{ marginBottom: '10px' }}>Password:</label>
            <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ marginBottom: '20px', padding: '10px', fontSize: '16px' }}/>
            <br/>

            <button onClick={handleSubmit} style={{ padding: '10px 20px', fontSize: '16px', backgroundColor: '#007BFF', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>Sign Up</button>
        </div>
    );
};

export default SignupPage;
import React, {useEffect} from "react";
import QRCode from "qrcode.react";
import {useState} from "react";
import {useLocation} from "react-router-dom";

const QRgen = () => {
    const location = useLocation();

    const id = location.state.id;
    const username = location.state.username;
    const gym = location.state.gym;

    const [QRValue, setQRValue] = useState('');

    const generateQRValue = () => {
        const currentTime = new Date().getTime();
        setQRValue(`UserID: ${id}, Time: ${currentTime}, Gym: ${gym}`);
    }

    useEffect(() => {
        generateQRValue();
        const interval = setInterval(() => {
            generateQRValue();
        }, 60000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            height: '100vh'
        }}>
            <div style={{marginTop: '20px', marginBottom: '20px'}}>
                <QRCode value={QRValue} size={256} />
            </div>
            <div style={{marginTop: '20px', fontSize: '18px', color: '#007BFF'}}>
                <p>QR Code updates every 60 seconds</p>
            </div>
        </div>
    );
}

export default QRgen;
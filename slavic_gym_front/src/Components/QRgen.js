import React, { useEffect, useState } from "react";
import QRCode from "qrcode.react";
import { useLocation, useNavigate } from "react-router-dom";
import '../Css/QRgen.css';  // Import the CSS file

const QRgen = () => {
    const location = useLocation();
    const navigate = useNavigate();

    const id = location.state.id;
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
    }, [id, gym]);

    const handleReturnClick = () => {
        navigate('/interface', { state: { id: id } });
    };

    return (
        <div className="qrgen-container">
            <div className="qrgen-qrcode">
                <QRCode value={QRValue} size={256} />
            </div>
            <div className="qrgen-info">
                <p>QR Code updates every 60 seconds</p>
            </div>
            <button
                className="qrgen-button"
                onClick={handleReturnClick}
            >
                Return
            </button>
        </div>
    );
}

export default QRgen;

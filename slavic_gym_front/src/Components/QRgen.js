import React, { useEffect, useState } from "react";
import QRCode from "qrcode.react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import '../Css/QRgen.css';  // Import the CSS file

const QRgen = () => {
    console.log('Component rendering');

    const location = useLocation();
    const navigate = useNavigate();

    const { id, gym } = location.state;

    const [QRValue, setQRValue] = useState('');
    const [qrGenerated, setQrGenerated] = useState(false);

    useEffect(() => {
        if (!qrGenerated) {
            console.log('Generating QR Value');
            generateQRValue();
        }

    }, [id, gym, qrGenerated]);

    const generateQRValue = async () => {
        try {
            const response = await axios.post('http://localhost:8080/visits/add', { memberId: id });

            if (response.data === "Visit added successfully.") {
                const currentTime = new Date().getTime();
                setQRValue(`UserID: ${id}, Time: ${currentTime}, Gym: ${gym}`);
                setQrGenerated(true); // Mark QR as generated
            } else {
                alert(response.data);
                navigate('/interface', { state: { id: id } });
                return; // Exit early if there's an error
            }
        } catch (error) {
            console.error('Error generating QR code:', error);
            alert('An error occurred while generating the QR code.'); // Alert for error
            navigate('/interface', { state: { id: id } });
            return; // Exit early if there's an error
        }
    }

    const handleReturnClick = () => {
        navigate('/interface', { state: { id: id } });
    };

    return (
        <div className="qrgen-container">
            <div className="qrgen-qrcode">
                <QRCode value={QRValue} size={256} />
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

import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../Css/Cart.css'; // Import CSS file
import axios from 'axios';

const Cart = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const id = location.state.id;
    const gym = location.state.gym;
    const cartItems = location.state.cartItems || [];
    const maxQuantities = location.state.maxQuantities || [];
    const itemNames = location.state.itemNames || {};
    const [quantities, setQuantities] = useState({});
    const [totalPrice, setTotalPrice] = useState(0);


    console.log('Cart Items', cartItems);
    console.log('Max Quantities', maxQuantities);

    useEffect(() => {
        console.log('LOLOLOLOLOLOLOLOLOLOL');
        // Initialize quantities and calculate initial total price
        let initialQuantities = {};
        let initialTotalPrice = 0;

        cartItems.forEach(item => {
            initialQuantities[item.id_item] = item.quantity;
            initialTotalPrice += item.quantity * (item.price || 0);
        });

        setQuantities(initialQuantities);
        setTotalPrice(initialTotalPrice);
        localStorage.setItem('cartQuantities', JSON.stringify(initialQuantities));
    }, [cartItems]);

    const incrementQuantity = (itemId, itemPrice) => {
        setQuantities(prevQuantities => {
            const currentQuantity = prevQuantities[itemId] || 0;
            const maxQuantity = maxQuantities.find(maxItem => maxItem.id_item === itemId)?.max_quantity || 0;

            if (currentQuantity < maxQuantity) {
                const newQuantities = {
                    ...prevQuantities,
                    [itemId]: currentQuantity + 1
                };
                setTotalPrice(totalPrice + (itemPrice || 0));
                console.log('Incrementing', newQuantities);
                localStorage.setItem('cartQuantities', JSON.stringify(newQuantities)); // Save quantities to local storage
                return newQuantities;
            } else {
                return prevQuantities;
            }
        });
    };

    const decrementQuantity = (itemId, itemPrice) => {
        setQuantities(prevQuantities => {
            const newQuantities = {
                ...prevQuantities,
                [itemId]: Math.max((prevQuantities[itemId] || 0) - 1, 0)
            };
            if (prevQuantities[itemId] > 0) {
                setTotalPrice(totalPrice - (itemPrice || 0));
            }
            console.log('Decrementing', newQuantities);
            localStorage.setItem('cartQuantities', JSON.stringify(newQuantities)); // Save quantities to local storage
            return newQuantities;
        });
    };

    const handleBackToShop = () => {
        const storedQuantities = localStorage.getItem('cartQuantities');
        navigate('/shop', { state: { id: id, gym: gym, returnedCartItems: storedQuantities } } );
    };

    const handleBuy = async () => {
        const response = await axios.post('http://localhost:8080/shop/buy', {
            id,
            gym,
            items: Object.keys(quantities).map(itemId => ({
                id_item: itemId,
                quantity: quantities[itemId]
            }))
        });

        console.log(response);

        if (response.data.success) {
            alert('Thank you for your purchase!');
            localStorage.clear();
            navigate('/shop', { state: { id: id, gym: gym, cartItems: [] } });
        }
        else {
            const name = itemNames[response.data.erroredItem];

            const responseForErroredItem = await axios.post('http://localhost:8080/shop/getbyiditem', { "itemId": response.data.erroredItem, "gymId": parseInt(gym) });
            console.log(responseForErroredItem);

            alert(`Something went wrong with your purchase. We have only ${responseForErroredItem.data.quantity} ${name} left in stock. Please try again.`);
        }
    };

    return (
        <div className="cart-container">
            <h1>Shopping Cart</h1>
            <div className="cart-list">
                {cartItems.length > 0 ? (
                    cartItems.map(item => (
                        <div className="cart-item" key={item.id_item}>
                            <img
                                src={require(`../ImagesForShop/${item.id_item}.png`)}
                                alt={item.item_name}
                                className="cart-item-image"
                            />
                            <div className="item-details">
                                <div className="item-info">
                                    <h2>{item.item_name}</h2>
                                    <p>Price: ${item.price ? item.price.toFixed(2) : '0.00'}</p>
                                </div>
                                <div className="item-counter">
                                    <button onClick={() => decrementQuantity(item.id_item, item.price)}>-</button>
                                    <span>{quantities[item.id_item] || 0}</span>
                                    <button onClick={() => incrementQuantity(item.id_item, item.price)}>+</button>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>Your cart is empty.</p>
                )}
            </div>
            <div className="total-price">
                <h2>Total Price: ${totalPrice.toFixed(2)}</h2>
            </div>
            <button onClick={handleBuy}>Buy</button>
            <button onClick={handleBackToShop}>Back to Shop</button>
        </div>
    );
};

export default Cart;

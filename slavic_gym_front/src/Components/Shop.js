import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../Css/Shop.css';
import cartIcon from '../ImagesForShop/cart.png';

const Shop = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const id = location.state.id;
    const gym = location.state.gym;
    const returnedCartItems = location.state.cartItems || [];
    const [inventory, setInventory] = useState([]);
    const [quantities, setQuantities] = useState({});
    const [cartItems, setCartItems] = useState(returnedCartItems || []);

    console.log('Gym: ', gym);

    const intGym = parseInt(gym);
    console.log('IntGym: ', intGym);

    console.log('Returned Cart Items: ', returnedCartItems);
    console.log('Cart Items: ', cartItems);

    useEffect(() => {
        const getInventory = async () => {
            try {
                const response = await axios.post('http://localhost:8080/shop/getallbyid', { intGym });
                if (Array.isArray(response.data)) {
                    setInventory(response.data);
                } else {
                    alert('Something went wrong');
                }
            } catch (error) {
                console.error('Error fetching inventory:', error);
                alert('Something went wrong');
            }
        };

        getInventory();
        console.log(inventory);
    }, [id]); // Make sure to include id as a dependency

    const incrementQuantity = (itemId) => {
        setQuantities(prevQuantities => {
            const currentQuantity = prevQuantities[itemId] || 0;
            const item = inventory.find(item => item.id_item === itemId);
            if (item && currentQuantity < item.quantity) {
                return {
                    ...prevQuantities,
                    [itemId]: currentQuantity + 1
                };
            }
            return prevQuantities;
        });
    };

    const decrementQuantity = (itemId) => {
        setQuantities(prevQuantities => ({
            ...prevQuantities,
            [itemId]: Math.max((prevQuantities[itemId] || 0) - 1, 0)
        }));
    };

    const handleAddingToCart = (itemId) => {
        const quantity = quantities[itemId] || 0;
        if (quantity > 0) {
            const item = inventory.find(item => item.id_item === itemId);
            setCartItems(prevCartItems => {
                const existingItem = prevCartItems.find(cartItem => cartItem.id_item === itemId);
                if (existingItem) {
                    return prevCartItems.map(cartItem =>
                        cartItem.id_item === itemId ? { ...cartItem, quantity: cartItem.quantity + quantity } : cartItem
                    );
                } else {
                    return [...prevCartItems, { ...item, quantity }];
                }
            });

            // Reset the quantity for the specific item to 0
            setQuantities(prevQuantities => ({
                ...prevQuantities,
                [itemId]: 0
            }));
        } else {
            alert('Please select a quantity greater than 0');
        }
    };

    const handleCartClick = () => {
        const maxQuantities = inventory.map(item => ({ id_item: item.id_item, max_quantity: item.quantity }));
        navigate('/cart', { state: { id: id, gym: gym, cartItems: cartItems, maxQuantities } });
    };

    return (
        <div className="shop-container">
            <h1>Gym Shop</h1>
            <div className="cart-button-container">
                <button className="cart-button" onClick={handleCartClick}>
                    <img src={cartIcon} alt="Cart" className="cart-icon"/>
                    <span>{cartItems.reduce((acc, item) => acc + item.quantity, 0)}</span>
                </button>
            </div>
            <div className="inventory-list">
                {inventory.length > 0 ? (
                    inventory.map(item => (
                        <div className="inventory-item" key={item.id_item}>
                            <img
                                src={require(`../ImagesForShop/${item.id_item}.png`)}
                                alt={item.item_name}
                                className="inventory-item-image"
                                style={{filter: item.quantity > 0 ? 'none' : 'grayscale(100%)'}}
                            />
                            <h2>{item.item_name}</h2>
                            {item.quantity > 0 ? (
                                <React.Fragment>
                                    <div className="item-counter">
                                        <button onClick={() => decrementQuantity(item.id_item)}>-</button>
                                        <span>{quantities[item.id_item] || 0}</span>
                                        <button onClick={() => incrementQuantity(item.id_item)}>+</button>
                                    </div>
                                    <button className="buy-button" onClick={() => handleAddingToCart(item.id_item)}>Add
                                        to Cart
                                    </button>
                                </React.Fragment>
                            ) : (
                                <p>Product is expired</p>
                            )}
                        </div>
                    ))
                ) : (
                    <p>No items available.</p>
                )}
            </div>

        </div>
    );
};

export default Shop;

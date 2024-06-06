import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../Css/Shop.css';
import cartIcon from '../ImagesForShop/cart.png';

const Shop = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { id, gym, returnedCartItems = [] } = location.state;

    const [inventory, setInventory] = useState([]);
    const [quantities, setQuantities] = useState({});
    const [cartItems, setCartItems] = useState([]);
    const [categories, setCategories] = useState({});
    const [selectedCategory, setSelectedCategory] = useState('All');
    const [filteredInventory, setFilteredInventory] = useState([]);

    const intGym = parseInt(gym);

    useEffect(() => {
        const getCategories = async (itemIds) => {
            try {
                const response = await axios.post('http://localhost:8080/shop/getcategories', itemIds);
                setCategories(response.data);
                console.log('Categories:', response.data);
            } catch (error) {
                console.error('Error fetching categories:', error);
                alert('Something went wrong');
            }
        };

        const getInventory = async () => {
            try {
                const response = await axios.post('http://localhost:8080/shop/getallbyid', { intGym });
                if (Array.isArray(response.data)) {
                    setInventory(response.data);
                    setFilteredInventory(response.data);
                    const itemIds = response.data.map(item => item.id_item);
                    getCategories(itemIds);
                } else {
                    console.error('Inventory response is not an array:', response.data);
                    alert('Something went wrong while fetching inventory');
                }
            } catch (error) {
                console.error('Error fetching inventory:', error);
                alert('Something went wrong');
            }
        };

        getInventory();
    }, [intGym]);

    useEffect(() => {
        const storedQuantities = localStorage.getItem('cartQuantities');
        if (storedQuantities && inventory.length > 0) {
            setQuantities(JSON.parse(storedQuantities));
            setCartItems(inventory.map(item => {
                const quantity = JSON.parse(storedQuantities)[item.id_item] || 0;
                if (quantity > 0) {
                    return {
                        id_gym: gym,
                        id_item: item.id_item,
                        item_name: item.item_name,
                        price: item.price,
                        quantity: quantity
                    };
                }
                return null;
            }).filter(Boolean));
        }
    }, [inventory]);

    const incrementQuantity = (itemId) => {
        setQuantities(prevQuantities => {
            const currentQuantity = prevQuantities[itemId] || 0;
            const item = inventory.find(item => item.id_item === itemId);
            if (item && currentQuantity < item.quantity) {
                const newQuantity = currentQuantity + 1;
                const newQuantities = {
                    ...prevQuantities,
                    [itemId]: newQuantity
                };
                localStorage.setItem('cartQuantities', JSON.stringify(newQuantities));
                return newQuantities;
            }
            return prevQuantities;
        });
    };

    const decrementQuantity = (itemId) => {
        setQuantities(prevQuantities => {
            const newQuantity = Math.max((prevQuantities[itemId] || 0) - 1, 0);
            const newQuantities = {
                ...prevQuantities,
                [itemId]: newQuantity
            };
            localStorage.setItem('cartQuantities', JSON.stringify(newQuantities));
            return newQuantities;
        });
    };

    const handleAddingToCart = (itemId) => {
        const quantity = quantities[itemId] || 0;
        if (quantity > 0) {
            const item = inventory.find(item => item.id_item === itemId);
            setCartItems(prevCartItems => {
                const existingItem = prevCartItems.find(cartItem => cartItem.id_item === itemId);
                if (existingItem) {
                    return prevCartItems.map(cartItem =>
                        cartItem.id_item === itemId ? { ...cartItem, quantity: quantity } : cartItem
                    );
                } else {
                    return [...prevCartItems, { ...item, quantity }];
                }
            });
        } else {
            alert('Please select a quantity greater than 0');
        }
    };

    const handleCartClick = () => {
        const maxQuantities = inventory.map(item => ({ id_item: item.id_item, max_quantity: item.quantity }));
        const itemNames = inventory.reduce((acc, item) => {
            acc[item.id_item] = item.item_name;
            return acc;
        }, {});
        navigate('/cart', { state: { id, gym, cartItems, maxQuantities, itemNames } });
    };

    const handleCategoryChange = (e) => {
        const selectedCategory = e.target.value;
        setSelectedCategory(selectedCategory);

        if (selectedCategory === 'All') {
            setFilteredInventory(inventory);
        } else {
            setFilteredInventory(inventory.filter(item => categories[item.id_item].category_name === selectedCategory));
        }
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
            <div className="category-filter">
                <label htmlFor="category">Filter by Category:</label>
                <select id="category" value={selectedCategory} onChange={handleCategoryChange}>
                    <option value="All">All</option>
                    {Object.values(categories).map(category => (
                        <option key={category.id_category} value={category.category_name}>{category.category_name}</option>
                    ))}
                </select>
            </div>
            <div className="inventory-list">
                {filteredInventory.length > 0 ? (
                    filteredInventory.map(item => (
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
                                    <button className="buy-button" onClick={() => handleAddingToCart(item.id_item)}>Add to Cart</button>
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

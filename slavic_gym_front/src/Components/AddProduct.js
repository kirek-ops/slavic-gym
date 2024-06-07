import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../Css/AddProduct.css'; // Import CSS file
import { useLocation, useNavigate } from 'react-router-dom';

const AddProduct = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { id, hasPosition, gym } = location.state;

    console.log(id, hasPosition, gym);

    const [selectedGym, setSelectedGym] = useState('');
    const [formData, setFormData] = useState({
        product_name: '',
        price: 0,
        quantity: 0,
        id_category: 0,
        id_gym: ''
    });
    const [selectedFile, setSelectedFile] = useState(null);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const realIdCategory = categories.find(category => category.category_name === formData.id_category);
        const dataToSend = new FormData();
        dataToSend.append('item_name', formData.product_name);
        dataToSend.append('quantity', formData.quantity);
        dataToSend.append('id_gym', parseInt(formData.id_gym));
        dataToSend.append('price', formData.price);
        dataToSend.append('image', selectedFile);
        dataToSend.append('id_category', parseInt(realIdCategory.id_category));

        const response = await axios.post('http://localhost:8080/shop/additem', dataToSend);
        console.log(response);
    };

    const handleGymChange = (e) => {
        setSelectedGym(e.target.value);
        setFormData({
            ...formData,
            id_gym: e.target.value
        });
    };

    const allowedGyms = gym.filter(gym => {
        const FDAGym = hasPosition['Front Desk Attendant'] || [];
        const managerGym = hasPosition['Manager'] || [];
        return FDAGym.includes(gym.id_gym) || managerGym.includes(gym.id_gym);
    });

    console.log(allowedGyms);

    const [selectedCategory, setSelectedCategory] = useState('');
    const [categories, setCategories] = useState([]);
    const getCategories = async () => {
        try {
            const response = await axios.post('http://localhost:8080/shop/getallcategories');
            setCategories(response.data);
            console.log('Categories:', response.data);
        } catch (error) {
            console.error('Error fetching categories:', error);
            alert('Something went wrong');
        }
    };

    useEffect(() => {
        getCategories();
    }, []);

    console.log(categories);

    const handleReturnClick = () => {
        navigate('/interface', { state: { id } });
    }

    return (
        <div className="add-product-container">
            <h2>Add Product</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="id_gym">Select Gym</label>
                    <select name="id_gym" id="id_gym" value={selectedGym} onChange={handleGymChange}>
                        <option value="">Select Gym</option>
                        {allowedGyms.map((gym) => (
                            <option key={gym.id_gym} value={gym.id_gym}>{gym.name}</option>
                        ))}
                    </select>
                </div>
                {selectedGym && (
                    <>
                        <div className="form-group">
                            <label htmlFor="product_name">Product Name</label>
                            <input type="text" name="product_name" id="product_name" value={formData.product_name}
                                   onChange={handleChange}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="price">Price</label>
                            <input type="number" name="price" id="price" value={formData.price}
                                   onChange={handleChange}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="quantity">Quantity</label>
                            <input type="number" name="quantity" id="quantity" value={formData.quantity}
                                   onChange={handleChange}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="id_category">Category</label>
                            <select name="id_category" id="id_category" value={formData.id_category}
                                    onChange={handleChange}>
                                <option value="">Select Category</option>
                                {categories.map((category) => (
                                    <option key={category.category_id} value={category.category_id}>
                                        {category.category_name}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="image">Product Image</label>
                            <input type="file" name="image" id="image" onChange={handleFileChange}/>
                        </div>
                        <button type="submit">Submit</button>
                    </>
                )}
            </form>
            <button onClick={handleReturnClick} className="return-button">Return</button>
        </div>
    );
};

export default AddProduct;

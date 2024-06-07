import React, { useState } from 'react';

const AddProduct = () => {
    const [product, setProduct] = useState({
        itemName: '',
        quantity: '',
        idGym: '',
        price: '',
        idCategory: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct({
            ...product,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Product added:', product);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="itemName">Item Name:</label>
                <input
                    type="text"
                    id="itemName"
                    name="itemName"
                    value={product.itemName}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="quantity">Quantity:</label>
                <input
                    type="number"
                    id="quantity"
                    name="quantity"
                    value={product.quantity}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="idGym">Gym ID:</label>
                <input
                    type="number"
                    id="idGym"
                    name="idGym"
                    value={product.idGym}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label htmlFor="price">Price:</label>
                <input
                    type="number"
                    id="price"
                    name="price"
                    value={product.price}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="idCategory">Category ID:</label>
                <input
                    type="number"
                    id="idCategory"
                    name="idCategory"
                    value={product.idCategory}
                    onChange={handleChange}
                />
            </div>
            <button type="submit">Add Product</button>
        </form>
    );
};

export default AddProduct;

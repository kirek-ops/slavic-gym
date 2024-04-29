-- Create gyms table
CREATE TABLE gyms (
    id_club INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(15),
    manager_id INT
);

-- Create employees table
CREATE TABLE employees (
    id_employee INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    hire_date DATE NOT NULL,
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES gyms(id_club)
);

-- Create gym_members table
CREATE TABLE gym_members (
    id_member INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    join_date DATE NOT NULL,
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES gyms(id_club)
);

-- Create classes table
CREATE TABLE classes (
    id_class INT PRIMARY KEY,
    class_name VARCHAR(255) NOT NULL,
    schedule DATE NOT NULL,
    id_club INT,
    capacity INT,
    instructor_id INT,
    FOREIGN KEY (id_club) REFERENCES gyms(id_club),
    FOREIGN KEY (instructor_id) REFERENCES employees(id_employee)
);

-- Create bookings table
CREATE TABLE bookings (
    id_booking INT PRIMARY KEY,
    id_member INT,
    id_class INT,
    booking_date DATE NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_class) REFERENCES classes(id_class)
);

-- Create inventory table
CREATE TABLE inventory (
    id_item INT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES gyms(id_club)
);

-- Create transactions table
CREATE TABLE transactions (
    id_transaction INT PRIMARY KEY,
    id_member INT,
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

--
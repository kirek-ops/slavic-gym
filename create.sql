-- Create gyms table
CREATE TABLE gyms (
    id_gym INT PRIMARY KEY,
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
    id_gym INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym)
);

-- Create gym_members table
CREATE TABLE gym_members (
    id_member INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    join_date DATE NOT NULL,
    id_gym INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym)
);

-- Create classes table
CREATE TABLE classes (
    id_class INT PRIMARY KEY,
    class_name VARCHAR(255) NOT NULL,
    schedule DATE NOT NULL,
    id_gym INT,
    capacity INT,
    id_instructor INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym),
    FOREIGN KEY (id_instructor) REFERENCES employees(id_employee)
);

-- Create bookings table
CREATE TABLE bookings (
    id_booking INT PRIMARY KEY,
    id_member INT,
    id_class INT,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_class) REFERENCES classes(id_class)
);

-- Create inventory table
CREATE TABLE inventory (
    id_item INT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    id_gym INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym)
);

-- Create memberships table
CREATE TABLE memberships (
    id_membership INT PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    price INT NOT NULL,
    duration INT NOT NULL -- DAYS
);

-- Create client membership relation
CREATE TABLE client_membership (
    id_member INT REFERENCES gym_members(id_member),
    id_membership INT REFERENCES memberships(id_membership),
    start_date DATE NOT NULL
);

-- Create transactions for memberships table
CREATE TABLE transactions_memberships (
     id_membership INT REFERENCES memberships(id_membership),
     id_member INT REFERENCES gym_members(id_member),
     order_date DATE NOT NULL,
     order_time TIMESTAMPTZ NOT NULL
);

-- Create transactions for inventory table
CREATE TABLE transactions_inventory (
     id_item INT REFERENCES inventory(id_item),
     id_member INT REFERENCES gym_members(id_member),
     order_date DATE NOT NULL,
     order_time TIMESTAMPTZ NOT NULL,
     quantity INTEGER NOT NULL
);  

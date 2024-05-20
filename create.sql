-- Create gyms table
CREATE TABLE gyms (
    id_gym INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    contact_number VARCHAR(15)
);

-- Create employees table
CREATE TABLE employees (
    id_employee INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

-- Create gym_managers table to track many-to-many relationship with date
CREATE TABLE gym_managers (
    id_gym INT,
    id_manager INT,
    start_date DATE NOT NULL,
    end_date DATE,
    PRIMARY KEY (id_gym, id_manager, start_date),
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym),
    FOREIGN KEY (id_manager) REFERENCES employees(id_employee)
);

-- Create positions table
CREATE TABLE positions (
    id_position INT PRIMARY KEY,
    position_name VARCHAR(50) NOT NULL
);

-- Create employee_positions table to track many-to-many relationship with date
CREATE TABLE employee_positions (
    id_employee INT,
    id_position INT,
    start_date DATE NOT NULL,
    end_date DATE,
    PRIMARY KEY (id_employee, id_position, start_date),
    FOREIGN KEY (id_employee) REFERENCES employees(id_employee),
    FOREIGN KEY (id_position) REFERENCES positions(id_position)
);

-- Create gym_members table
CREATE TABLE gym_members (
    id_member INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    join_date DATE NOT NULL
);

-- Create member_employees table to track employees who are also members
CREATE TABLE member_employees (
    id_member INT,
    id_employee INT,
    PRIMARY KEY (id_member, id_employee),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_employee) REFERENCES employees(id_employee)
);

-- Create classes table with time_from and time_till
CREATE TABLE classes (
    id_class INT PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    schedule TIMESTAMP NOT NULL,
    time_from TIME NOT NULL,
    time_till TIME NOT NULL,
    id_gym INT,
    capacity INT NOT NULL,
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
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    id_gym INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym)
);

-- Create memberships table
CREATE TABLE memberships (
    id_membership INT PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration INT NOT NULL -- DAYS
);

-- Create client_membership table to track membership start dates
CREATE TABLE client_membership (
    id_member INT,
    id_membership INT,
    start_date DATE NOT NULL,
    PRIMARY KEY (id_member, id_membership, start_date),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_membership) REFERENCES memberships(id_membership)
);

-- Create transactions for memberships table
CREATE TABLE transactions_memberships (
    id_transaction INT PRIMARY KEY,
    id_membership INT,
    id_member INT,
    order_time TIMESTAMPTZ NOT NULL,
    FOREIGN KEY (id_membership) REFERENCES memberships(id_membership),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

-- Create transactions for inventory table
CREATE TABLE transactions_inventory (
    id_transaction INT PRIMARY KEY,
    id_item INT,
    id_member INT,
    order_time TIMESTAMPTZ NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_item) REFERENCES inventory(id_item),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

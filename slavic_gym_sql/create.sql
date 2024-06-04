-- Drop all tables if they exist to start fresh
CREATE TABLE gyms (
    id_gym INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    contact_number VARCHAR(15)
);

CREATE TABLE gym_members (
    id_member INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    join_date DATE NOT NULL,
    passwd VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
    id_employee INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_employee) REFERENCES gym_members(id_member)
);

CREATE TABLE positions (
    id_position INT PRIMARY KEY,
    position_name VARCHAR(50) NOT NULL
);

CREATE TABLE employee_positions (
    id_employee INT,
    id_position INT,
    id_gym INT,
    start_date DATE NOT NULL,
    end_date DATE,
    PRIMARY KEY (id_employee, id_position, id_gym, start_date),
    FOREIGN KEY (id_employee) REFERENCES employees(id_employee),
    FOREIGN KEY (id_position) REFERENCES positions(id_position),
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym)
);

CREATE TABLE classes (
    id_class INT PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    schedule DATE NOT NULL,
    time_from TIME NOT NULL,
    time_till TIME NOT NULL,
    id_gym INT,
    capacity INT NOT NULL,
    id_instructor INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym),
    FOREIGN KEY (id_instructor) REFERENCES employees(id_employee)
);

CREATE TABLE bookings (
    id_booking INT PRIMARY KEY,
    id_member INT,
    id_class INT,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_class) REFERENCES classes(id_class)
);

CREATE TABLE categories (
    id_category INT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    parent_category_id INT,
    FOREIGN KEY (parent_category_id) REFERENCES categories(id_category)
);

CREATE TABLE inventory (
    id_item INT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    id_gym INT,
    id_category INT,
    FOREIGN KEY (id_gym) REFERENCES gyms(id_gym),
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);

CREATE TABLE products_categories (
    id_item INT,
    id_category INT,
    PRIMARY KEY (id_item, id_category),
    FOREIGN KEY (id_item) REFERENCES inventory(id_item),
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);

CREATE TABLE memberships (
    name VARCHAR(100) NOT NULL,
    id_membership INT PRIMARY KEY,
    is_active BOOLEAN NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration INT NOT NULL
);

CREATE TABLE client_membership (
    id_member INT,
    id_membership INT,
    start_date DATE NOT NULL,
    PRIMARY KEY (id_member, id_membership, start_date),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_membership) REFERENCES memberships(id_membership)
);

CREATE TABLE transactions_memberships (
    id_transaction INT PRIMARY KEY,
    id_membership INT,
    id_member INT,
    order_time TIMESTAMPTZ NOT NULL,
    FOREIGN KEY (id_membership) REFERENCES memberships(id_membership),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

CREATE TABLE transactions_inventory (
    id_transaction INT PRIMARY KEY,
    id_item INT,
    id_member INT,
    order_time TIMESTAMPTZ NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_item) REFERENCES inventory(id_item),
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

CREATE TABLE access_codes (
    code_id VARCHAR(50) PRIMARY KEY,
    generated_at TIMESTAMPTZ NOT NULL,
    id_member INT,
    first_used_at TIMESTAMPTZ,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

CREATE TABLE visits (
    id_visit INT PRIMARY KEY,
    id_member INT,
    visit_time TIMESTAMPTZ NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member)
);

CREATE TABLE repetition_exercises (
    id_exercise INT PRIMARY KEY,
    exercise_name VARCHAR(100) NOT NULL
);

CREATE TABLE time_exercises (
    id_exercise INT PRIMARY KEY,
    exercise_name VARCHAR(100) NOT NULL
);

CREATE TABLE repetition_goals (
    id_goal INT PRIMARY KEY,
    id_member INT,
    id_exercise INT,
    target_reps INT NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_exercise) REFERENCES repetition_exercises(id_exercise)
);

CREATE TABLE time_goals (
    id_goal INT PRIMARY KEY,
    id_member INT,
    id_exercise INT,
    target_time INTERVAL NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_exercise) REFERENCES time_exercises(id_exercise)
);

CREATE TABLE exercise_logs_repetitions (
    id_log INT PRIMARY KEY,
    id_member INT,
    id_goal INT,
    log_date TIMESTAMPTZ NOT NULL,
    reps_done INT NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_goal) REFERENCES repetition_goals(id_goal)
);

CREATE TABLE exercise_logs_time (
    id_log INT PRIMARY KEY,
    id_member INT,
    id_goal INT,
    log_date TIMESTAMPTZ NOT NULL,
    time_done INTERVAL NOT NULL,
    FOREIGN KEY (id_member) REFERENCES gym_members(id_member),
    FOREIGN KEY (id_goal) REFERENCES time_goals(id_goal)
);

-- Insert sample data into gyms table
INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number) VALUES
(1, 'Downtown Fitness', '123 Main St', 'Metropolis', '12345', '123-456-7890'),
(2, 'Uptown Gym', '456 Elm St', 'Metropolis', '67890', '098-765-4321');

-- Insert sample data into employees table
INSERT INTO employees (id_employee, first_name, last_name) VALUES
(1, 'John', 'Doe'),
(2, 'Jane', 'Smith'),
(3, 'Emily', 'Johnson');

-- Insert sample data into gym_managers table
INSERT INTO gym_managers (id_gym, id_manager, start_date, end_date) VALUES
(1, 1, '2023-01-01', NULL),
(2, 2, '2023-02-01', NULL);

-- Insert sample data into positions table
INSERT INTO positions (id_position, position_name) VALUES
(1, 'Trainer'),
(2, 'Receptionist');

-- Insert sample data into employee_positions table
INSERT INTO employee_positions (id_employee, id_position, start_date, end_date) VALUES
(1, 1, '2023-01-01', NULL),
(2, 2, '2023-02-01', NULL),
(3, 1, '2023-03-01', NULL);

-- Insert sample data into gym_members table
INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date) VALUES
(1, 'Alice', 'Brown', 'alice.brown@example.com', '123-123-1234', '2023-01-10'),
(2, 'Bob', 'Green', 'bob.green@example.com', '234-234-2345', '2023-02-20');

-- Insert sample data into member_employees table
INSERT INTO member_employees (id_member, id_employee) VALUES
(1, 1),
(2, 2);

-- Insert sample data into classes table
INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor) VALUES
(1, 'Yoga', '2023-05-20 10:00:00', '10:00:00', '11:00:00', 1, 20, 1),
(2, 'Pilates', '2023-05-21 11:00:00', '11:00:00', '12:00:00', 2, 15, 3);

-- Insert sample data into bookings table
INSERT INTO bookings (id_booking, id_member, id_class) VALUES
(1, 1, 1),
(2, 2, 2);

-- Insert sample data into inventory table
INSERT INTO inventory (id_item, item_name, quantity, id_gym) VALUES
(1, 'Yoga Mat', 50, 1),
(2, 'Dumbbell Set', 20, 2);

-- Insert sample data into memberships table
INSERT INTO memberships (id_membership, is_active, price, duration) VALUES
(1, TRUE, 50.00, 30),
(2, TRUE, 90.00, 60);

-- Insert sample data into client_membership table
INSERT INTO client_membership (id_member, id_membership, start_date) VALUES
(1, 1, '2023-01-15'),
(2, 2, '2023-02-25');

-- Insert sample data into transactions_memberships table
INSERT INTO transactions_memberships (id_transaction, id_membership, id_member, order_time) VALUES
(1, 1, 1, '2023-01-15 09:00:00'),
(2, 2, 2, '2023-02-25 10:00:00');

-- Insert sample data into transactions_inventory table
INSERT INTO transactions_inventory (id_transaction, id_item, id_member, order_time, quantity) VALUES
(1, 1, 1, '2023-01-15 09:05:00', 1),
(2, 2, 2, '2023-02-25 10:05:00', 2);

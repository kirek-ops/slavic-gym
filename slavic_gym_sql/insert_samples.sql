-- Insert sample data into gyms table
INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number)
VALUES
    (1, 'Fitness World', 'Aleja 3 Maja 15', 'Krakow', '1234554', '123-456-7890'),
    (2, 'Healthy Life Gym', 'Jagiellonska 52', 'Nowy Sacz', '6789022', '987-654-3210'),
    (3, 'New generation gym', 'Jagiellonska 4', 'Nowy Sacz', '6789023', '999-999-9999'),
    (4, 'Absolute best gym', 'Sliska 14', 'Krakow', '67589023', '999-777-9999'),
    (5, 'Campus Platinum', 'St Lojasiewicza 6', 'Krakow', '67589023', '999-999-8888');

INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date, passwd) 
VALUES 
    ((SELECT nextval('id_sequence')), 'John', 'Doe', 'john@example.com', '1234567890', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Jane', 'Smith', 'jane@example.com', '0987654321', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Michael', 'Johnson', 'michael@example.com', '1112223333', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Emily', 'Williams', 'emily@example.com', '3334445555', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'James', 'Brown', 'james@example.com', '5556667777', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Sarah', 'Taylor', 'sarah@example.com', '7778889999', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'David', 'Martinez', 'david@example.com', '9990001111', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Jennifer', 'Garcia', 'jennifer@example.com', '2223334444', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Daniel', 'Lopez', 'daniel@example.com', '4445556666', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'),
    ((SELECT nextval('id_sequence')), 'Jessica', 'Hernandez', 'jessica@example.com', '6667778888', '2024-06-03', '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS');

INSERT INTO positions (id_position, position_name) 
VALUES 
    (1, 'Trainer'),
    (2, 'Front Desk Attendant'),
    (3, 'Manager'),
    (4, 'Cleaner'),
    (5, 'Nutritionist');

INSERT INTO employees (id_employee, first_name, last_name) VALUES
    (1, 'John', 'Doe'),
    (2, 'Jane', 'Smith'),
    (7, 'David', 'Martinez');

INSERT INTO employee_positions (id_employee, id_position, id_gym, start_date, end_date) VALUES
    (1, 1, 1, '2024-01-01', NULL),
    (2, 3, 2, '2023-11-07', '2024-10-11'),
    (2, 1, 5, '2023-11-07', NULL),
    (2, 5, 3, '2023-11-07', '2024-10-11'),
    (7, 4, 4, '2019-03-08', NULL);

INSERT INTO memberships (name, id_membership, is_active, price, duration) VALUES
    ('SUMMER FRESH', 1, true, 50.00, 30),
    ('SUMMER SUPER FRESH', 2, true, 100.00, 90),
    ('ID_TOP', 3, false, 150.00, 180);

-- Insert categories
INSERT INTO categories (id_category, category_name, parent_category_id) VALUES
                                                                            (1, 'Supplements', NULL),
                                                                            (2, 'Proteins', 1),
                                                                            (3, 'Vitamins', 1),
                                                                            (4, 'Equipment', NULL),
                                                                            (5, 'Weights', 4),
                                                                            (6, 'Cardio Machines', 4);

-- Insert inventory items
INSERT INTO inventory (id_item, item_name, quantity, id_gym, price) VALUES
                                                                              (1, 'Whey Protein', 50, 1, 67.59),
                                                                              (2, 'Multivitamin', 100, 1, 50.49),
                                                                              (3, 'Dumbbells Set', 30, 1, 150.49),
                                                                              (4, 'Treadmill', 1, 1, 1299.99),
                                                                              (5, 'Water', 10, 5, 99.99),
                                                                              (6, 'R-Weller Shot', 0, 1, 9.99);

-- Insert product categories
INSERT INTO products_categories (id_item, id_category) VALUES
                                                           (1, 2),
                                                           (2, 3),
                                                           (3, 5),
                                                           (4, 6);

-- Insert transaction inventory
INSERT INTO transactions_inventory (id_transaction, id_item, id_member, order_time, quantity) VALUES
                                                                                                  (1, 1, 1, '2024-06-01 10:00:00', 2),
                                                                                                  (2, 2, 2, '2024-06-02 11:30:00', 1),
                                                                                                  (3, 3, 1, '2024-06-03 12:00:00', 3),
                                                                                                  (4, 4, 3, '2024-06-04 14:45:00', 1);

-- Insert classes ensuring that only trainers and managers are assigned as instructors
INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor)
VALUES
    (1, 'Yoga Basics', '2024-06-10', '08:00:00', '09:00:00', 1, 20, 1), -- John Doe (Trainer)
    (2, 'Advanced Cardio', '2024-06-10', '10:00:00', '11:30:00', 2, 30, 2), -- Jane Smith (Manager)
    (3, 'Strength Training', '2024-06-11', '12:00:00', '13:30:00', 5, 25, 2), -- Jane Smith (Trainer)
    (4, 'Pilates', '2024-06-12', '14:00:00', '15:00:00', 1, 20, 1), -- John Doe (Trainer)
    (5, 'Spinning', '2024-06-13', '16:00:00', '17:00:00', 5, 15, 2), -- Jane Smith (Trainer)
    (6, 'HIIT Workout', '2024-06-14', '18:00:00', '19:00:00', 1, 25, 1), -- John Doe (Trainer)
    (7, 'Dance Aerobics', '2024-06-15', '19:30:00', '20:30:00', 2, 30, 2), -- Jane Smith (Manager)
    (8, 'Meditation', '2024-06-16', '07:00:00', '08:00:00', 1, 15, 1), -- John Doe (Trainer)
    (9, 'CrossFit', '2024-06-17', '09:00:00', '10:30:00', 2, 20, 2), -- Jane Smith (Manager)
    (10, 'Boxing', '2024-06-18', '11:00:00', '12:30:00', 1, 10, 1); -- John Doe (Trainer)
-- Insert sample data into gyms table
INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number)
VALUES
   (1, 'Fitness World', '123 Main St', 'Anytown', '12345', '123-456-7890'),
    (2, 'Healthy Life Gym', '456 Elm St', 'Othertown', '67890', '987-654-3210');

-- Insert sample data into employees table
INSERT INTO employees (id_employee, first_name, last_name) VALUES
                                                               (1, 'John', 'Doe'),
                                                               (2, 'Jane', 'Smith'),
                                                               (3, 'Alice', 'Johnson');

-- Insert sample data into gym_managers table
INSERT INTO gym_managers (id_gym, id_manager, start_date, end_date) VALUES
                                                                        (1, 1, '2020-01-01', NULL),
                                                                        (2, 2, '2021-02-01', NULL);

-- Insert sample data into positions table
INSERT INTO positions (id_position, position_name) VALUES
                                                       (1, 'Manager'),
                                                       (2, 'Instructor'),
                                                       (3, 'Cleaner');

-- Insert sample data into employee_positions table
INSERT INTO employee_positions (id_employee, id_position, start_date, end_date) VALUES
                                                                                    (1, 1, '2020-01-01', NULL),
                                                                                    (2, 2, '2021-02-01', NULL),
                                                                                    (3, 3, '2022-03-01', NULL);

-- Insert sample data into gym_members table
INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date, passwd) VALUES
                                                                                                       (1, 'Mark', 'Twain', 'mark.twain@example.com', '111-222-3333', '2022-01-01', 'password123'),
                                                                                                       (2, 'Lucy', 'Liu', 'lucy.liu@example.com', '444-555-6666', '2023-01-01', 'password456');

-- Insert sample data into member_employees table
INSERT INTO member_employees (id_member, id_employee) VALUES
                                                          (1, 2),
                                                          (2, 3);


-- Insert sample data into classes table
INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor) VALUES
                                                                                                                (1, 'Yoga', '2023-06-01 09:00:00', '09:00', '10:00', 1, 20, 2),
                                                                                                                (2, 'Pilates', '2023-06-02 10:00:00', '10:00', '11:00', 2, 15, 3);

-- Insert sample data into bookings table
INSERT INTO bookings (id_booking, id_member, id_class) VALUES
                                                           (1, 1, 1),
                                                           (2, 2, 2);

-- Insert sample data into categories table
INSERT INTO categories (id_category, category_name, parent_category_id) VALUES
                                                                            (1, 'Fitness Equipment', NULL),
                                                                            (2, 'Cardio Machines', 1),
                                                                            (3, 'Weights', 1);

-- Insert sample data into inventory table
INSERT INTO inventory (id_item, item_name, quantity, id_gym, id_category) VALUES
                                                                              (1, 'Treadmill', 5, 1, 2),
                                                                              (2, 'Dumbbell Set', 10, 2, 3);


-- Insert sample data into products_categories table
INSERT INTO products_categories (id_item, id_category) VALUES
                                                           (1, 2),
                                                           (2, 3);

-- Insert sample data into memberships table
INSERT INTO memberships (id_membership, is_active, price, duration) VALUES
                                                                        (1, TRUE, 29.99, 30),
                                                                        (2, FALSE, 99.99, 365);

-- Insert sample data into client_membership table
INSERT INTO client_membership (id_member, id_membership, start_date) VALUES
                                                                         (1, 1, '2023-01-01'),
                                                                         (2, 2, '2023-01-01');

-- Insert sample data into transactions_memberships table
INSERT INTO transactions_memberships (id_transaction, id_membership, id_member, order_time) VALUES
                                                                                                (1, 1, 1, '2023-01-01 10:00:00+00'),
                                                                                                (2, 2, 2, '2023-01-02 11:00:00+00');

-- Insert sample data into transactions_inventory table
INSERT INTO transactions_inventory (id_transaction, id_item, id_member, order_time, quantity) VALUES
                                                                                                  (1, 1, 1, '2023-01-01 10:30:00+00', 1),
                                                                                                  (2, 2, 2, '2023-01-02 11:30:00+00', 2);

-- Insert sample data into access_codes table
INSERT INTO access_codes (code_id, generated_at, id_member, first_used_at) VALUES
                                                                               ('CODE123', '2023-01-01 10:00:00+00', 1, '2023-01-01 10:05:00+00'),
                                                                               ('CODE456', '2023-01-02 11:00:00+00', 2, '2023-01-02 11:05:00+00');

-- Insert sample data into visits table
INSERT INTO visits (id_visit, id_member, visit_time) VALUES
                                                         (1, 1, '2023-01-01 10:00:00+00'),
                                                         (2, 2, '2023-01-02 11:00:00+00');

-- Insert sample data into repetition_exercises table
INSERT INTO repetition_exercises (id_exercise, exercise_name) VALUES
                                                                  (1, 'Push-ups'),
                                                                  (2, 'Sit-ups');

-- Insert sample data into time_exercises table
INSERT INTO time_exercises (id_exercise, exercise_name) VALUES
                                                            (1, 'Plank'),
                                                            (2, 'Running');

-- Insert sample data into repetition_goals table
INSERT INTO repetition_goals (id_goal, id_member, id_exercise, target_reps) VALUES
                                                                                (1, 1, 1, 50),
                                                                                (2, 2, 2, 100);

-- Insert sample data into time_goals table
INSERT INTO time_goals (id_goal, id_member, id_exercise, target_time) VALUES
                                                                          (1, 1, 1, '00:05:00'),
                                                                          (2, 2, 2, '00:30:00');

-- Insert sample data into exercise_logs_repetitions table
INSERT INTO exercise_logs_repetitions (id_log, id_member, id_goal, log_date, reps_done) VALUES
                                                                                            (1, 1, 1, '2023-01-01 10:00:00+00', 30),
                                                                                            (2, 2, 2, '2023-01-02 11:00:00+00', 50);


-- Insert sample data into exercise_logs_time table
INSERT INTO exercise_logs_time (id_log, id_member, id_goal, log_date, time_done) VALUES
                                                                                     (1, 1, 1, '2023-01-01 10:00:00+00', '00:03:00'),
                                                                                     (2, 2, 2, '2023-01-02 11:00:00+00', '00:20:00');

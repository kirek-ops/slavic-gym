INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number) VALUES
        (1, 'Fitness World', 'Aleja 3 Maja 15', 'Krakow', '1234554', '123-456-7890'),
        (2, 'Healthy Life Gym', 'Jagiellonska 52', 'Nowy Sacz', '6789022', '987-654-3210'),
        (3, 'New generation gym', 'Jagiellonska 4', 'Nowy Sacz', '6789023', '999-999-9999'),
        (4, 'Absolute best gym', 'Sliska 14', 'Krakow', '67589023', '999-777-9999'),
        (5, 'Campus Platinum', 'St Lojasiewicza 6', 'Krakow', '67589023', '999-999-8888'),
        (6, 'Fit Nation', 'Grunwaldzka 27', 'Gdansk', '1234567', '111-222-3333'),
        (7, 'Pure Fitness', 'Kosciuszki 11', 'Warsaw', '8765432', '444-555-6666'),
        (8, 'Iron Strong Gym', 'Nowa 15', 'Wroclaw', '3456789', '777-888-9999'),
        (9, 'Flex Gym', 'Starowislna 22', 'Krakow', '9876543', '222-333-4444'),
        (10, 'Powerhouse Gym', 'Jana Pawla 33', 'Katowice', '4567890', '555-666-7777'),
        (11, 'Gym Max', 'Plac Wolnosci 8', 'Poznan', '1357924', '888-999-0000'),
        (12, 'Fit4Life', 'Sienkiewicza 10', 'Warsaw', '2468135', '333-444-5555'),
        (13, 'Elite Fitness', 'Wielicka 44', 'Krakow', '9876543', '666-777-8888'),
        (14, 'Bodyworks Gym', 'Dolnych Mlynow 3', 'Wroclaw', '2345678', '999-888-7777'),
        (15, 'Pulse Fitness', 'Kosciuszki 7', 'Szczecin', '7654321', '222-333-4444'),
        (16, 'Fit Zone', 'Piotrkowska 77', 'Lodz', '5432167', '111-222-3333'),
        (17, 'Urban Fitness', 'Grunwaldzka 1', 'Gdansk', '1234567', '555-444-3333'),
        (18, 'Stronghold Gym', 'Pawia 5', 'Krakow', '3456789', '777-999-1111'),
        (19, 'Fitness Plus', 'Warszawska 21', 'Warsaw', '8765432', '888-777-6666'),
        (20, 'Body Sculpt', 'Wielicka 33', 'Krakow', '9876543', '999-888-7777'),
        (21, 'Core Fitness', 'Dluga 12', 'Wroclaw', '2345678', '666-555-4444'),
        (22, 'Fit4U', 'Mickiewicza 1', 'Poznan', '1357924', '333-222-1111'),
        (23, 'Peak Performance', 'Lipowa 6', 'Warsaw', '2468135', '111-222-3333'),
        (24, 'Total Fitness', 'Kosciuszki 55', 'Krakow', '9876543', '444-555-6666'),
        (25, 'GymNation', 'Starowislna 88', 'Krakow', '8765432', '777-888-9999'),
        (26, 'Vitality Gym', 'Grunwaldzka 17', 'Gdansk', '1234567', '888-999-0000'),
        (27, 'FitSpace', 'Pawia 3', 'Warsaw', '3456789', '222-333-4444'),
        (28, 'Health Club', 'Warszawska 12', 'Krakow', '9876543', '333-444-5555'),
        (29, 'Flex Fitness', 'Wielicka 11', 'Krakow', '9876543', '555-666-7777'),
        (30, 'Body Balance', 'Dluga 88', 'Wroclaw', '2345678', '666-777-8888'),
        (31, 'FitLife', 'Mickiewicza 3', 'Poznan', '1357924', '777-888-9999'),
        (32, 'Fitness Hub', 'Lipowa 22', 'Warsaw', '2468135', '888-999-0000'),
        (33, 'Power Fit Gym', 'Kosciuszki 33', 'Krakow', '9876543', '999-000-1111'),
        (34, 'Body & Soul', 'Starowislna 9', 'Krakow', '8765432', '000-111-2222'),
        (35, 'City Gym', 'Grunwaldzka 5', 'Gdansk', '1234567', '111-222-3333'),
        (36, 'FitFlex', 'Pawia 7', 'Warsaw', '3456789', '222-333-4444'),
        (37, 'Healthy Living', 'Warszawska 15', 'Krakow', '9876543', '333-444-5555'),
        (38, 'Golden Fitness', 'Wielicka 99', 'Wroclaw', '2345678', '444-555-6666'),
        (39, 'Body Blast', 'Dluga 23', 'Poznan', '1357924', '555-666-7777'),
        (40, 'Flex and Fitness', 'Lipowa 44', 'Warsaw', '2468135', '666-777-8888');



------


CREATE OR REPLACE FUNCTION insert_gym_members(num_members INT) RETURNS VOID AS $$
DECLARE
    first_names TEXT[] := ARRAY['John', 'Jane', 'Michael', 'Emily', 'James', 'Sarah', 'David', 'Jennifer', 'Daniel', 'Jessica', 'William', 'Olivia', 'Noah', 'Emma', 'Liam', 'Ava', 'Elijah', 'Sophia', 'Oliver', 'Isabella', 'Benjamin', 'Mia', 'Lucas', 'Charlotte', 'Mason', 'Amelia', 'Ethan', 'Harper', 'Alexander', 'Evelyn', 'Henry', 'Abigail', 'Jackson', 'Emily', 'Sebastian', 'Elizabeth', 'Aiden', 'Ella', 'Matthew', 'Camila', 'Samuel', 'Luna', 'David', 'Sofia', 'Daniel', 'Aiden', 'Joseph', 'Grace', 'Mila'];
    last_names TEXT[] := ARRAY['Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Garcia', 'Miller', 'Davis', 'Rodriguez', 'Martinez', 'Hernandez', 'Lopez', 'Gonzalez', 'Wilson', 'Anderson', 'Thomas', 'Taylor', 'Moore', 'Jackson', 'Martin', 'Lee', 'Perez', 'Thompson', 'White', 'Harris', 'Sanchez', 'Clark', 'Ramirez', 'Lewis', 'Robinson', 'Walker', 'Young', 'Allen', 'King', 'Wright', 'Scott', 'Torres', 'Nguyen', 'Hill', 'Flores', 'Green', 'Adams', 'Nelson', 'Baker', 'Hall', 'Rivera', 'Campbell', 'Mitchell', 'Carter', 'Roberts'];
    email_domain TEXT := '@example.com';
    i INT;
    j INT;
    member_count INT := 0;
BEGIN
    FOR i IN 1..array_length(first_names, 1) LOOP
            FOR j IN 1..array_length(last_names, 1) LOOP
                    IF member_count >= num_members THEN
                        RETURN;
                    END IF;

                    INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date, passwd)
                    VALUES (
                               (SELECT nextval('id_gym_members')),
                               first_names[i],
                               last_names[j],
                               lower(first_names[i] || '.' || last_names[j] || email_domain),
                               LPAD(FLOOR(RANDOM() * 1000000000)::TEXT, 10, '0'),
                               CURRENT_DATE - FLOOR(RANDOM() * 365) * Interval '1 day',
                               '$2a$10$KcO4Tsv.4EzpPpnlvNhqIuvqkK5wpUJl51VZ9pG.urw0scLe.NXJS'
                           );
                    member_count := member_count + 1;
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_gym_members(1000);

------

INSERT INTO positions (id_position, position_name)
VALUES
    (1, 'Trainer'),
    (2, 'Front Desk Attendant'),
    (3, 'Manager'),
    (4, 'Cleaner'),
    (5, 'Nutritionist');

------


CREATE OR REPLACE FUNCTION assign_roles_to_employees()
    RETURNS VOID AS $$
DECLARE
    rec RECORD;
    position_ids INT[] := ARRAY[1, 2, 3, 4, 5];
    random_position INT;
    random_gym INT;
    start_date DATE;
BEGIN
    -- Ensure there are no duplicates and only 100 members are selected
    FOR rec IN
        SELECT id_member, first_name, last_name
        FROM gym_members
        ORDER BY RANDOM()
        LIMIT 100
        LOOP
            -- Randomly assign a position to each selected member
            random_position := position_ids[FLOOR(RANDOM() * 5) + 1];
            -- Randomly assign a gym id from 1 to 40
            random_gym := FLOOR(RANDOM() * 40) + 1;

            -- Insert into employees table
            INSERT INTO employees (id_employee, first_name, last_name)
            VALUES (rec.id_member, rec.first_name, rec.last_name)
            ON CONFLICT (id_employee) DO NOTHING;

            start_date := CURRENT_DATE - FLOOR(RANDOM() * 365) * Interval '1 day';
            -- Assign position and gym to employee
            INSERT INTO employee_positions (id_employee, id_position, id_gym, start_date, end_date)
            VALUES (rec.id_member, random_position, random_gym, start_date, start_date + 365 * Interval '1 day');
        END LOOP;
END $$ LANGUAGE plpgsql;

SELECT assign_roles_to_employees();


--------


CREATE OR REPLACE FUNCTION insert_memberships()
    RETURNS VOID AS $$
DECLARE
    membership_names VARCHAR[] := ARRAY['Basic', 'Silver', 'Gold', 'Platinum', 'Diamond', 'Premium', 'Elite', 'Pro', 'Exclusive', 'Ultimate'];
    i INT := 1;
BEGIN
    WHILE i <= 10 LOOP
            INSERT INTO memberships (name, id_membership, is_active, price, duration)
            VALUES (
                       membership_names[i],
                       (SELECT nextval('id_membership')),
                       true,
                       (50.00 * i),
                       (30 * i)
                   );
            i := i + 1;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_memberships();


------


INSERT INTO categories (id_category, category_name) VALUES
                                                        (1, 'Supplements'),
                                                        (2, 'Proteins'),
                                                        (3, 'Vitamins'),
                                                        (4, 'Equipment'),
                                                        (5, 'Weights'),
                                                        (6, 'Cardio Machines'),
                                                        (7, 'Apparel'),
                                                        (8, 'Accessories'),
                                                        (9, 'Fitness Trackers'),
                                                        (10, 'Healthy Snacks');



------


CREATE OR REPLACE FUNCTION insert_inventory_items() RETURNS VOID AS $$
DECLARE
    i INTEGER;
    products TEXT[] := ARRAY[
        'Whey Protein', 'Multivitamin', 'Dumbbells Set', 'Treadmill',
        'Yoga Mat', 'Exercise Bike', 'Resistance Bands', 'Kettlebell Set',
        'Protein Bars', 'Fish Oil Capsules', 'Pull-up Bar', 'Foam Roller',
        'Jump Rope', 'Creatine Powder', 'Pre-Workout Supplement',
        'BCAA Powder', 'Electrolyte Drink', 'Massage Ball',
        'Power Rack', 'Weight Bench', 'Rowing Machine', 'Elliptical Trainer',
        'Gym Bag', 'Shaker Bottle', 'Yoga Block', 'Resistance Tube',
        'Weightlifting Belt', 'Training Gloves', 'Ankle Weights', 'Foam Yoga Roller',
        'Hand Grips', 'Ab Roller', 'Pull-up Assist Band', 'Protein Shaker'
        ];
    categories INTEGER[] := ARRAY[
        2, 3, 5, 6, 4, 6, 8, 5, 10, 3, 4, 4, 8, 2, 1, 2, 1, 8, 4, 4, 6, 6, 7, 8, 4, 8, 8, 8, 8, 8, 8, 4, 8, 8
        ];
    product_name TEXT;
    category_id INTEGER;
    quantity INTEGER;
    gym_id INTEGER;
    price DECIMAL(10, 2);
BEGIN
    FOR i IN 1..100 LOOP
            -- Choose a random item from the products array
            product_name := products[FLOOR(RANDOM() * ARRAY_LENGTH(products, 1) + 1)];
            category_id := categories[ARRAY_POSITION(products, product_name)];

            quantity := 10 + (i % 20); -- Random quantity between 10 and 29
            gym_id := 1 + (i % 40); -- Gym ID between 1 and 40
            price := ROUND(10 + (i % 100) * 5.77, 2); -- Random price between 10 and 577.00

            BEGIN
                -- Insert the item into the inventory table
                INSERT INTO inventory (id_item, item_name, quantity, id_gym, price)
                VALUES (i, product_name, quantity, gym_id, price);

                -- Assign the category to the item
                INSERT INTO products_categories (id_item, id_category)
                VALUES (i, category_id);

            EXCEPTION
                WHEN unique_violation THEN
                    -- Handle unique constraint violation
--                     RAISE NOTICE 'Item % already exists in gym %', product_name, gym_id;
            END;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_inventory_items();


------


CREATE OR REPLACE FUNCTION insert_classes() RETURNS VOID AS $$
DECLARE
    i INTEGER;
    class_names TEXT[] := ARRAY[
        'Yoga Basics', 'Advanced Cardio', 'Strength Training', 'Pilates',
        'Spinning', 'HIIT Workout', 'Dance Aerobics', 'Meditation',
        'CrossFit', 'Boxing', 'Zumba', 'Bootcamp', 'Body Pump', 'Tai Chi',
        'Kickboxing', 'Barre', 'Aqua Aerobics', 'Stretching', 'Tabata', 'TRX',
        'Body Sculpt', 'Circuit Training', 'Functional Training', 'Jazzercise',
        'Power Yoga', 'Pilates Fusion', 'Step Aerobics', 'Core Blast', 'Martial Arts', 'Ballet Fitness'
        ];
    chosen_gym INTEGER;
    capacity INTEGER;
    id_instructor INTEGER;
    start_time TIME;
    end_time TIME;
    class_date DATE;
    class_name TEXT;
BEGIN
    FOR i IN 1..100 LOOP
            -- Randomly select a gym id between 1 and 40
            chosen_gym := FLOOR(RANDOM() * 40 + 1);

            -- Randomly select a class name
            class_name := class_names[FLOOR(RANDOM() * ARRAY_LENGTH(class_names, 1) + 1)];

            -- Randomly select a capacity between 10 and 30
            capacity := 10 + FLOOR(RANDOM() * 21);

            -- Randomly select a date for the class (from 30 days in the past to 30 days in the future)
            class_date := CURRENT_DATE + (FLOOR(RANDOM() * 61) - 40) * INTERVAL '1 day';

            -- Randomly select a time for the class (between 6:00 AM and 8:00 PM)
            start_time := TIME '06:00:00' + (FLOOR(RANDOM() * 8400) * INTERVAL '1 second');
            end_time := start_time + (FLOOR(RANDOM() * 5400 + 3600) * INTERVAL '1 second'); -- Duration between 1 and 2.5 hours

            BEGIN
                -- Randomly select an instructor who is either a Trainer or Manager at the specific gym
                SELECT e.id_employee
                INTO id_instructor
                FROM employees e
                         JOIN employee_positions ep ON e.id_employee = ep.id_employee
                         JOIN positions p ON ep.id_position = p.id_position
                WHERE ep.id_gym = chosen_gym
                  AND p.position_name IN ('Trainer', 'Manager')
                ORDER BY RANDOM()
                LIMIT 1;

                -- Insert the class into the classes table
                IF id_instructor IS NOT NULL THEN
                    BEGIN
                        INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor)
                        VALUES ((SELECT nextval('id_classes')), class_name, class_date, start_time, end_time, chosen_gym, capacity, id_instructor);
                    EXCEPTION
                        WHEN others THEN
                            -- Handle any other exceptions
--                             RAISE NOTICE 'Error inserting class for gym %: %', chosen_gym, SQLERRM;
                    END;
                ELSE
--                     RAISE NOTICE 'No suitable instructor found for gym %', chosen_gym;
                END IF;
            EXCEPTION
                WHEN others THEN
                    -- Handle any other exceptions
--                     RAISE NOTICE 'Error selecting instructor for gym %: %', chosen_gym, SQLERRM;
            END;
        END LOOP;
END;
$$ LANGUAGE plpgsql;


SELECT insert_classes();


------


CREATE OR REPLACE FUNCTION add_client_memberships()
    RETURNS VOID AS $$
DECLARE
    num_clients INT;
    client_id INT;
    membership_count INT;
    membership_id INT;
    membership_duration INT;
    start_date_new DATE;
BEGIN
    -- Get the total number of clients
    SELECT COUNT(*) INTO num_clients FROM gym_members;

    -- Loop through each client
    FOR client_id IN 1..num_clients LOOP
            -- Generate a random number of memberships for the client (up to 2)
            membership_count := FLOOR(RANDOM() * 3); -- 0, 1, or 2 memberships

            -- Loop to add memberships for the client
            FOR i IN 1..membership_count LOOP
                    -- Choose a random membership
                    SELECT id_membership, duration INTO membership_id, membership_duration
                    FROM memberships
                    ORDER BY RANDOM()
                    LIMIT 1;

                    -- Generate a random start date for the membership (within the last year)
                    start_date_new := CURRENT_DATE - FLOOR(RANDOM() * 365) * Interval '1 day';

                    -- Check if the client already has an active membership of the same type
                    IF NOT EXISTS (
                        SELECT 1
                        FROM client_membership
                        WHERE id_member = client_id AND id_membership = membership_id
                          AND (start_date + membership_duration) > CURRENT_DATE
                    ) THEN
                        -- Add the membership for the client
                        INSERT INTO client_membership (id_member, id_membership, start_date)
                        VALUES (client_id, membership_id, start_date_new);
                    END IF;
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT add_client_memberships();


------


CREATE OR REPLACE FUNCTION add_member_visits_past()
    RETURNS VOID AS $$
DECLARE
    max_visits_per_user INT := 3;
    max_visit_time_offset INT := 30; -- Maximum offset in days for visit times
    member_id INT;
    membership_id INT;
    membership_start_date DATE;
    membership_duration INT;
    visit_time TIMESTAMP WITH TIME ZONE;
BEGIN
    -- Loop through each member
    FOR member_id IN (SELECT id_member FROM gym_members) LOOP
            -- Get all memberships (active and expired) for the member
            FOR membership_id, membership_start_date, membership_duration IN
                SELECT cm.id_membership, cm.start_date, m.duration
                FROM client_membership cm
                         INNER JOIN memberships m ON cm.id_membership = m.id_membership
                WHERE cm.id_member = member_id
                LOOP
                    -- Generate up to 5 visits for each membership, including past periods
                    FOR i IN 1..max_visits_per_user LOOP
                            BEGIN
                                -- Generate a random visit time within the duration of the membership
                                visit_time := (membership_start_date + (FLOOR(RANDOM() * membership_duration) * INTERVAL '1 day')) + (FLOOR(RANDOM() * (max_visit_time_offset - 1)) * INTERVAL '1 day');

                                -- Insert the visit record
                                INSERT INTO visits (id_visit, id_member, visit_time)
                                VALUES ((SELECT NEXTVAL('id_visit')), member_id, visit_time);
                            EXCEPTION
                                WHEN OTHERS THEN
                                    -- Raise a notice with the error message but continue with the next iteration
--                                     RAISE NOTICE 'An error occurred while inserting visit for member %, membership %: %', member_id, membership_id, SQLERRM;
                            END;
                        END LOOP;
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT add_member_visits_past();


------


CREATE OR REPLACE FUNCTION insert_bookings()
    RETURNS VOID AS $$
DECLARE
    id_member INT;
    id_class_booked INT;
    booking_count INT := 0;
    max_bookings INT := 1000; -- Max number of bookings to attempt
BEGIN
    FOR id_member IN 1..max_bookings LOOP
            FOR booking_count IN 1..(FLOOR(RANDOM() * 3) + 1) LOOP -- Each member can have 0 to 2 bookings
            BEGIN
                -- Randomly select a class
                SELECT id_class
                INTO id_class_booked
                FROM classes
                ORDER BY RANDOM()
                LIMIT 1;

                -- Attempt to insert the booking
                INSERT INTO bookings (id_booking, id_member, id_class)
                VALUES (
                           (SELECT nextval('id_bookings')),
                           id_member,
                           id_class_booked
                       );
            EXCEPTION
                WHEN unique_violation THEN
                -- Handle unique constraint violation
--                     RAISE NOTICE 'Booking already exists or conflicts with another booking for member %, class %', id_member, id_class;
                WHEN check_violation THEN
                -- Handle check constraint violation
--                     RAISE NOTICE 'Booking violates a check constraint for member %, class %', id_member, id_class;
                WHEN foreign_key_violation THEN
                -- Handle foreign key constraint violation
--                     RAISE NOTICE 'Booking violates a foreign key constraint for member %, class %', id_member, id_class;
                WHEN others THEN
                -- Handle any other exceptions
--                     RAISE NOTICE 'An error occurred while inserting booking for member %, class %', id_member, id_class;
            END;

                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Call the function to insert bookings
SELECT insert_bookings();


------


CREATE OR REPLACE FUNCTION insert_repetition_exercises()
    RETURNS VOID AS $$
DECLARE
    exercise_names VARCHAR[] := ARRAY[
        'Push-ups', 'Sit-ups', 'Squats', 'Lunges', 'Burpees',
        'Plank', 'Jumping Jacks', 'Mountain Climbers', 'Bicycle Crunches',
        'Tricep Dips', 'Russian Twists', 'Leg Raises', 'High Knees',
        'Wall Sits', 'Supermans'
        ];
    i INT;
    exercise_id INT;
BEGIN
    FOR i IN 1..ARRAY_LENGTH(exercise_names, 1) LOOP
            exercise_id := NEXTVAL('id_repetition_exercise'); -- Generate the next ID from the sequence
            INSERT INTO repetition_exercises (id_exercise, exercise_name)
            VALUES (exercise_id, exercise_names[i]);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Call the function to insert exercises
SELECT insert_repetition_exercises();


------


CREATE OR REPLACE FUNCTION insert_time_exercises()
    RETURNS VOID AS $$
DECLARE
    exercise_names VARCHAR[] := ARRAY[
        'Plank Hold', 'Wall Sit', 'Side Plank', 'Superman Hold', 'Bridge Hold',
        'Hollow Hold', 'L-Sit', 'Handstand Hold', 'Leg Raise Hold', 'Flutter Kicks',
        'Russian Twist', 'V-Sit Hold', 'Dead Hang', 'Scissor Kicks', 'Boat Pose'
        ];
    i INT;
    exercise_id INT;
BEGIN
    FOR i IN 1..ARRAY_LENGTH(exercise_names, 1) LOOP
            exercise_id := NEXTVAL('id_time_exercise'); -- Generate the next ID from the sequence
            INSERT INTO time_exercises (id_exercise, exercise_name)
            VALUES (exercise_id, exercise_names[i]);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Call the function to insert time exercises
SELECT insert_time_exercises();


------


CREATE OR REPLACE FUNCTION insert_repetition_goals()
    RETURNS VOID AS $$
DECLARE
    max_goals_per_member INT := 5; -- Maximum number of repetition goals per member
    member_id INT;
    exercise_id INT;
BEGIN
    -- Loop through each member
    FOR member_id IN (SELECT id_member FROM gym_members) LOOP
            -- Generate up to max_goals_per_member repetition goals for each member
            FOR i IN 1..max_goals_per_member LOOP
                    -- Randomly select an exercise
                    SELECT id_exercise INTO exercise_id FROM repetition_exercises ORDER BY RANDOM() LIMIT 1;

                    -- Insert the repetition goal record
                    INSERT INTO repetition_goals (id_goal, id_member, id_exercise, reps_target)
                    VALUES ((SELECT NEXTVAL('id_rep_goal')), member_id, exercise_id, (FLOOR(RANDOM() * 20) + 5)); -- Random target reps between 5 and 25
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_repetition_goals();


------


CREATE OR REPLACE FUNCTION insert_time_goals()
    RETURNS VOID AS $$
DECLARE
    max_goals_per_member INT := 5; -- Maximum number of time goals per member
    member_id INT;
    exercise_id INT;
BEGIN
    -- Loop through each member
    FOR member_id IN (SELECT id_member FROM gym_members) LOOP
            -- Generate up to max_goals_per_member time goals for each member
            FOR i IN 1..max_goals_per_member LOOP
                    -- Randomly select an exercise
                    SELECT id_exercise INTO exercise_id FROM time_exercises ORDER BY RANDOM() LIMIT 1;

                    -- Insert the time goal record
                    INSERT INTO time_goals (id_goal, id_member, id_exercise, minutes_target)
                    VALUES ((SELECT NEXTVAL('id_time_goal')), member_id, exercise_id, (FLOOR(RANDOM() * 60) + 10)); -- Random target time between 5 and 65 minutes
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_time_goals();


------


CREATE OR REPLACE FUNCTION insert_exercise_logs_repetitions()
    RETURNS VOID AS $$
DECLARE
    max_logs_per_member INT := 10; -- Maximum number of logs per member
    member_id INT;
    exercise_id INT;
BEGIN
    -- Loop through each member
    FOR member_id IN (SELECT id_member FROM gym_members) LOOP
            -- Generate up to max_logs_per_member logs for each member
            FOR i IN 1..max_logs_per_member LOOP
                    -- Randomly select an exercise
                    SELECT id_exercise INTO exercise_id FROM repetition_exercises ORDER BY RANDOM() LIMIT 1;

                    -- Insert the log record
                    INSERT INTO exercise_logs_repetitions (id_log, id_member, id_exercise, log_date, reps_done)
                    VALUES ((SELECT NEXTVAL('id_logs_reps')), member_id, exercise_id, CURRENT_DATE - (FLOOR(RANDOM() * 90) * INTERVAL '1 day'), (FLOOR(RANDOM() * 50) + 10)); -- Random reps between 10 and 60
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_exercise_logs_repetitions();


------


CREATE OR REPLACE FUNCTION insert_exercise_logs_time()
    RETURNS VOID AS $$
DECLARE
    max_logs_per_member INT := 10; -- Maximum number of logs per member
    member_id INT;
    exercise_id INT;
BEGIN
    -- Loop through each member
    FOR member_id IN (SELECT id_member FROM gym_members) LOOP
            -- Generate up to max_logs_per_member logs for each member
            FOR i IN 1..max_logs_per_member LOOP
                    -- Randomly select an exercise
                    SELECT id_exercise INTO exercise_id FROM time_exercises ORDER BY RANDOM() LIMIT 1;

                    -- Insert the log record
                    INSERT INTO exercise_logs_time (id_log, id_member, id_exercise, log_date, minutes_done)
                    VALUES ((SELECT NEXTVAL('id_logs_time')), member_id, exercise_id, CURRENT_DATE - (FLOOR(RANDOM() * 90) * INTERVAL '1 day'), (FLOOR(RANDOM() * 90) + 10)); -- Random minutes between 10 and 100
                END LOOP;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_exercise_logs_time();






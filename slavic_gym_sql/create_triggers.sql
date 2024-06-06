--Inserting a new membership for a client if the client has bought a membership
CREATE OR REPLACE FUNCTION insert_client_membership_trigger_function()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO client_membership (id_member, id_membership, start_date)
    VALUES (NEW.id_member, NEW.id_membership, NEW.order_time);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_transactions_memberships
    AFTER INSERT ON transactions_memberships
    FOR EACH ROW EXECUTE PROCEDURE insert_client_membership_trigger_function();


-- Checking if the user has any active memberships at the moment
CREATE OR REPLACE FUNCTION check_active_membership()
    RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM client_membership cm
                 INNER JOIN memberships m ON cm.id_membership = m.id_membership
        WHERE cm.id_member = NEW.id_member
          AND m.id_membership = NEW.id_membership
          AND (cm.start_date + m.duration) > CURRENT_DATE
    ) THEN
        RETURN NEW;
    ELSE
        RETURN NULL;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER ensure_active_membership
    BEFORE INSERT ON transactions_memberships
    FOR EACH ROW
EXECUTE FUNCTION check_active_membership();


-- Create a function to check if the instructor is employed as an instructor or manager at the specific gym
CREATE OR REPLACE FUNCTION check_instructor_position_validity() RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM employee_positions ep
                 JOIN positions p ON ep.id_position = p.id_position
        WHERE ep.id_employee = NEW.id_instructor
          AND ep.id_gym = NEW.id_gym
          AND ep.start_date <= NEW.schedule
          AND (ep.end_date IS NULL OR ep.end_date >= NEW.schedule)
          AND p.position_name IN ('Trainer', 'Manager')
    ) THEN
        RAISE EXCEPTION 'The instructor is not employed as an instructor or manager at the specified gym.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to enforce the check constraint before inserting into the classes table
-- Raises exception in case the instructor is not employed as an instructor or manager at the specified gym
CREATE TRIGGER enforce_instructor_position_validity
    BEFORE INSERT ON classes
    FOR EACH ROW
EXECUTE FUNCTION check_instructor_position_validity();


-- Create or replace the function to check instructor class availability
CREATE OR REPLACE FUNCTION check_instructor_class_availability() RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM classes c
        WHERE c.id_instructor = NEW.id_instructor
          AND c.id_class <> NEW.id_class
          AND c.schedule = NEW.schedule
          AND (
            (NEW.time_from < c.time_till AND NEW.time_till > c.time_from) OR
            (c.time_from < NEW.time_till AND c.time_till > NEW.time_from)
            )
    ) THEN
        RAISE EXCEPTION 'The instructor has intersecting classes at the same time.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger to enforce the check constraint before inserting into the classes table
-- Raises exception in case the instructor has intersecting classes at the same time
CREATE TRIGGER enforce_instructor_class_availability
    BEFORE INSERT OR UPDATE ON classes
    FOR EACH ROW
EXECUTE FUNCTION check_instructor_class_availability();


-- Create or replace the trigger function to check if there's enough inventory before insertion
CREATE OR REPLACE FUNCTION check_inventory_before_insert()
    RETURNS TRIGGER AS $$
BEGIN
    -- Check if there's enough inventory available
    IF EXISTS (
        SELECT 1
        FROM inventory
        WHERE id_item = NEW.id_item
          AND quantity >= NEW.quantity
    ) THEN
        -- If there's enough inventory, allow the insertion
        RETURN NEW;
    ELSE
        -- If there's not enough inventory, raise an exception to prevent insertion
        RAISE EXCEPTION 'Insufficient inventory available for item %', NEW.id_item;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the check_inventory_before_insert function before insertion into transactions_inventory
CREATE TRIGGER check_inventory_trigger
    BEFORE INSERT ON transactions_inventory
    FOR EACH ROW
EXECUTE FUNCTION check_inventory_before_insert();


--Create a trigger to subtract the quantity of the item from the inventory when a new transaction is made
CREATE OR REPLACE FUNCTION update_inventory()
    RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        -- Subtract the purchased quantity from the inventory
        UPDATE inventory
        SET quantity = quantity - NEW.quantity
        WHERE id_item = NEW.id_item;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to execute the update_inventory function after insertion into transactions_inventory
CREATE TRIGGER update_inventory_trigger
    AFTER INSERT ON transactions_inventory
    FOR EACH ROW
EXECUTE FUNCTION update_inventory();


-- Create the function to check for duplicate bookings
CREATE OR REPLACE FUNCTION check_duplicate_booking()
    RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM bookings
        WHERE id_member = NEW.id_member
          AND id_class = NEW.id_class
    ) THEN
        RAISE EXCEPTION 'This member has already booked this class.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger to execute the function before inserting into bookings
CREATE TRIGGER check_duplicate_booking_trigger
    BEFORE INSERT ON bookings
    FOR EACH ROW
EXECUTE FUNCTION check_duplicate_booking();


-- Create the function to check class capacity
CREATE OR REPLACE FUNCTION check_class_capacity()
    RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM bookings WHERE id_class = NEW.id_class) >=
       (SELECT capacity FROM classes WHERE id_class = NEW.id_class) THEN
        RAISE EXCEPTION 'The class is full, cannot accept more bookings';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger to enforce class capacity
CREATE TRIGGER check_class_capacity_trigger
    BEFORE INSERT ON bookings
    FOR EACH ROW
EXECUTE FUNCTION check_class_capacity();


-- Step 1: Create the Trigger Function
CREATE OR REPLACE FUNCTION check_member_not_instructor()
    RETURNS TRIGGER AS $$
BEGIN
    -- Check if the member is not the instructor of the class
    IF NEW.id_member = (SELECT id_instructor FROM classes WHERE id_class = NEW.id_class) THEN
        RAISE EXCEPTION 'A member cannot book a class they are instructing';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Step 2: Create the Trigger
CREATE TRIGGER trigger_check_member_not_instructor
    BEFORE INSERT ON bookings
    FOR EACH ROW
EXECUTE FUNCTION check_member_not_instructor();



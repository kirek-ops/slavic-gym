ALTER TABLE classes
    ADD CONSTRAINT check_class_duration
        CHECK (time_till > time_from AND time_till >= time_from + INTERVAL '1 hour');

ALTER TABLE classes
    ADD CONSTRAINT check_time_till_later_than_time_from
        CHECK (time_till > time_from);

ALTER TABLE employee_positions
    ADD CONSTRAINT check_start_date_before_end_date
        CHECK ((end_date IS NULL) OR (start_date < end_date));

ALTER TABLE memberships
    ADD CONSTRAINT check_membership_duration
        CHECK (duration > 0);

ALTER TABLE classes
    ADD CONSTRAINT check_class_schedule_future
        CHECK (schedule > CURRENT_DATE OR (schedule = CURRENT_DATE AND time_from > CURRENT_TIME));

ALTER TABLE gym_members
    ADD CONSTRAINT unique_phone_number
        UNIQUE (phone_number);


ALTER TABLE inventory
    ADD CONSTRAINT unique_item_per_gym
        UNIQUE (id_gym, item_name);

ALTER TABLE classes
    ADD CONSTRAINT positive_capacity
        CHECK (capacity > 0);
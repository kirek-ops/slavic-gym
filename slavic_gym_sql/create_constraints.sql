ALTER TABLE classes
    ADD CONSTRAINT check_class_duration
        CHECK (time_till > time_from AND time_till >= time_from + INTERVAL '1 hour');

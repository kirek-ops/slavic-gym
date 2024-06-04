ALTER TABLE classes
    ADD CONSTRAINT check_duration
        CHECK (duration >= INTERVAL '1 hour');


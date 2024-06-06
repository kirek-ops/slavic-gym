CREATE OR REPLACE FUNCTION get_number_of_bookings_for_class(class_id INT)
    RETURNS INT AS $$
DECLARE
    booking_count INT;
BEGIN
    SELECT COUNT(*) INTO booking_count
    FROM bookings
    WHERE id_class = class_id;

    RETURN booking_count;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_first_date_when_goal_reached_time(goal_id INT)
    RETURNS DATE AS $$
        DECLARE
            id_ex INT := NULL;
            minutes_need INT := NULL;
            I exercise_logs_time%ROWTYPE;
        BEGIN
            SELECT id_exercise INTO id_ex
            FROM time_goals WHERE id_goal = goal_id;

            SELECT minutes_target INTO minutes_need
            FROM time_goals WHERE id_goal = goal_id;

            FOR I IN (SELECT * FROM exercise_logs_time ORDER BY log_date) LOOP
                IF I.id_exercise = id_ex AND I.minutes_done >= minutes_need THEN
                    return I.log_date;
                END IF;
            END LOOP;

            RETURN NULL;
        END;
    $$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_first_date_when_goal_reached_reps(goal_id INT)
    RETURNS DATE AS $$
        DECLARE
            id_ex INT := NULL;
            reps_need INT := NULL;
            I exercise_logs_repetitions%ROWTYPE;
        BEGIN
            SELECT id_exercise INTO id_ex
            FROM repetition_goals WHERE id_goal = goal_id;

            SELECT reps_target INTO reps_need
            FROM repetition_goals WHERE id_goal = goal_id;

            FOR I IN (SELECT * FROM exercise_logs_repetitions ORDER BY log_date) LOOP
                IF I.id_exercise = id_ex AND I.reps_done >= reps_need THEN
                    return I.log_date;
                END IF;
            END LOOP;

            RETURN NULL;
        END;
    $$ LANGUAGE plpgsql;
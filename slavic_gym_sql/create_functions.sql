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

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

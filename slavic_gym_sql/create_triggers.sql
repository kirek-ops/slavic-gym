CREATE OR REPLACE FUNCTION insert_client_membership_trigger_function()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO client_membership (id_member, id_membership, start_date)
    VALUES (NEW.id_member, NEW.id_membership, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_transactions_memberships
    AFTER INSERT ON transactions_memberships
    FOR EACH ROW EXECUTE PROCEDURE insert_client_membership_trigger_function();
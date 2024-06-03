-- Insert sample data into employees table
INSERT INTO employees (id_employee, first_name, last_name) VALUES
                                                               (1, 'John', 'Doe'),
                                                               (2, 'Jane', 'Smith'),
                                                               (3, 'Alice', 'Johnson');

INSERT INTO memberships (name, id_membership, is_active, price, duration) VALUES
('SUMMER FRESH', 1, true, 50.00, 30),
('LOL WEAK PUSSY', 2, true, 100.00, 90),
('KEK', 3, false, 150.00, 180);

INSERT INTO gyms (id_gym, name, street, city, postal_code, contact_number) VALUES
(1, 'Fitness World', 'Aleja 3 Maja 15', 'Krakow', '1234554', '123-456-7890'),
(2, 'Healthy Life Gym', 'Jagiellonska 52', 'Nowy Sacz', '6789022', '987-654-3210'),
(3, 'New generation gym', 'Jagiellonska 4', 'Nowy Sacz', '6789023', '999-999-9999'),
(4, 'Absolute best gym', 'Sliska 14', 'Krakow', '67589023', '999-777-9999'),
(5, 'Campus Platinum', 'St Lojasiewicza 6', 'Krakow', '67589023', '999-999-8888');

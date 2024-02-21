
INSERT INTO users (username, password, firstname, enabled, show_vegan, show_vegetarian, show_fish, show_meat) VALUES
('rick@novi.nl', '$2a$12$cRhwHa1NNuDsrwQ8FrXGPuXBdNsThW7.4qhkCYzbe1zAt0AokT3hy', 'rick', true, true, true, true, true),
('fred@novi.nl', '$2a$12$Iomjx6CbbYLwzAXJZa9Z8.G5HKTPvAROaLuXiyqnWRzQP9SibYUji', 'fred', true, false, false, true, true);

INSERT INTO authorities (username, authority) VALUES
('rick@novi.nl', 'ROLE_ADMIN'),
('fred@novi.nl', 'ROLE_USER');
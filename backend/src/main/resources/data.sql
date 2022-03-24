-- Initialisation des tables
INSERT INTO Country(code, name) VALUES
    ('FR', 'France'), -- Les clés sont auto-générées
    ('UK', 'United Kingdom'),
    ('US', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('Lyon', 2, SELECT id FROM Country WHERE code = 'FR'),
    ('Marseille', 2, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('Oxford', 1, SELECT id FROM Country WHERE code = 'UK'),
    ('Cambridge', 2, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US'),
    ('Los Angeles', 11, SELECT id FROM Country WHERE code = 'US'),
    ('San Francisco', 7, SELECT id FROM Country WHERE code = 'US');        
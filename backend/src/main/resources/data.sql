-- Initialisation des tables
INSERT INTO Corps(nom_partie) VALUES
    ('partie1'), -- Les clés sont auto-générées
    ('partie2');

--INSERT INTO Corps(nom_partie_compose_id, nom_partie_est_composee_id) VALUES
  --  (SELECT id FROM Corps WHERE nom_partie = 'partie1', SELECT id FROM Corps WHERE nom_partie = 'partie2');
   
INSERT INTO Maladie(nom_maladie,symptomes,description,facteur_aggravant,cim_10) VALUES
    ('Diabète','Nausées, soif,malaise',' Le diabète est une maladie chronique caractérisée par la présence d’un excès de sucre dans le sang appelé . Il est avéré si le taux de glycémie à jeun est égal ou supérieur à 1,26 g/l ou 7 mmol/l de sang lors de deux dosages successifs.','obésité', 4);

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

    INSERT INTO Soigner(valDuree, uniteDuree, valFreq,uniteFreq,doseParPrise) VALUES
    (1, 1, 3,0,1); 


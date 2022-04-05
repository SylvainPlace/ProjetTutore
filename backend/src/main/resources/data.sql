-- Initialisation des tables
INSERT INTO Corps(nom_partie) VALUES
    ('partie1'), -- Les clés sont auto-générées
    ('partie2'),
    ('pancréas');

INSERT INTO Composer(nom_partie_est_composee_id, nom_partie_compose_id) VALUES
(SELECT id FROM Corps WHERE nom_partie = 'partie1', SELECT id FROM Corps WHERE nom_partie = 'partie2');
   
INSERT INTO Maladie(nom_maladie,symptomes,description,facteur_aggravant,cim_10) VALUES
    ('Diabète','Nausées, soif,malaise',' Le diabète est une maladie chronique caractérisée par la présence d’un excès de sucre dans le sang appelé . Il est avéré si le taux de glycémie à jeun est égal ou supérieur à 1,26 g/l ou 7 mmol/l de sang lors de deux dosages successifs.','obésité', 3);

INSERT INTO MEDICAMENT( nom_medic, info_prise, contre_indications) VALUES
   ('Maxilase', 'sirop', 'deconseillé pour les patients intolerants au fructose');
  
INSERT INTO UTILISATEUR(adresse_mail, prenom, nom, mdp, date_de_naiss, categorie) VALUES
    ('adresse@mail', '5Cure', 'PentaPathologie', 'motDePasse', '01-01-2001', 0);


INSERT INTO Soigner(valDuree, uniteDuree, valFreq,uniteFreq,doseParPrise,medicament_id, utilisateur_id ,maladie_id  ) VALUES
    (1, 1, 3, 0, 1, SELECT id FROM medicament WHERE nom_medic = 'Maxilase', SELECT id FROM utilisateur WHERE adresse_mail = 'adresse@mail', SELECT id FROM maladie WHERE nom_maladie = 'Diabète'); 


INSERT INTO Toucher(maladie_id, corps_id) VALUES
   (SELECT id FROM Maladie WHERE nom_maladie = 'Diabète', SELECT id FROM Corps WHERE nom_partie ='pancréas');
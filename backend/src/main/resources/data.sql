-- Initialisation des tables
INSERT INTO Corps(nom_partie)
VALUES ('coeur'),
    ('poumons'),
    ('genou'),
    ('reins'),
    ('artères'),
    ('abdomens'),
    ('système tégumentaire'),
    ('système squelettique'),
    ('système musculaire'),
    ('système nerveux'),
    ('système endocrine'),
    ('système cardiovasculaire'),
    ('système lymphatique et immunitaire'),
    ('système respiratoire'),
    ('système digestif'),
    ('système excréteur ou urinaire et renal'),
    ('système reproducteur'),
    ('pancréas');
INSERT INTO Composer(
        nom_partie_est_composee_id,
        nom_partie_compose_id
    )
VALUES (
        SELECT id
        FROM Corps
        WHERE nom_partie = 'système cardiovasculaire',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'coeur'
    ),
    (
        SELECT id
        FROM Corps
        WHERE nom_partie = 'système excréteur ou urinaire et renal',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'reins'
    ),
    (
        SELECT id
        FROM Corps
        WHERE nom_partie = 'système digestif',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'pancréas'
    );
INSERT INTO Maladie(
        nom_maladie,
        symptomes,
        description,
        facteur_aggravant,
        cim_10
    )
VALUES (
        'Diabète',
        'Nausées, soif, malaise',
        ' Le diabète est une maladie chronique caractérisée par la présence d’un excès de sucre dans le sang appelé . Il est avéré si le taux de glycémie à jeun est égal ou supérieur à 1,26 g/l ou 7 mmol/l de sang lors de deux dosages successifs.',
        'obésité',
        3
    ),
    (
        'Arthrose',
        'douleur articulaire, courbatures et raideur matinale',
        'Maladie articulaire. Elle se caractérise par une destruction du cartilage qui s étend à toutes les structures de l articulation',
        'vieillissement ou la surcharge mécanique de l articulation',
        12
    ),
    (
        'Covid-19',
        'fièvre, essouflement, malaise, perte brutale de l’odorat',
        ' Maladie infectieuse émergente de type zoonose virale',
        'Age avancé, Cancer',
        21
    ),
    (
        'Hypertension artérielle',
        'maux de tête, vertiges, bourdonnements d oreille, des troubles de la vision',
        'Augmentation anormale de la pression du sang sur la paroi des artères',
        'Alimentation riche en sel, obésité, alcool',
        8
    );
INSERT INTO MEDICAMENT(nom_medic, info_prise, contre_indications)
VALUES (
        'Maxilase',
        'sirop',
        'deconseiller pour les patients intolerants au fructose'
    ),
    (
        'Glucophage',
        'comprimé',
        'deconseiller pour les patients avec une insuffisance rénale'
    ),
    (
        'Paxlovid',
        'Comprimé',
        'deconseiller pour les patients présentant une insuffisance hépatique sévère '
    ),
    (
        'Paracétamol',
        'Comprimé',
        'deconseiller pour les patients alcooliques ou en insuffisance hépatocellulaire'
    ),
    (
        'Spironolactone',
        'Comprimé',
        'deconseiller pour les patients présentant une insuffisance rénale grave'
    );
INSERT INTO UTILISATEUR(
        adresse_mail,
        prenom,
        nom,
        mdp,
        date_de_naiss,
        categorie
    )
VALUES (
        'adresse@gmail.com',
        'Micheline',
        'Fleury',
        'Micheline@0312',
        '1970-03-19',
        0
    ),
    (
        'adresse2@hotmail.fr',
        'Didier',
        'Morel',
        'motDePasse',
        '1950-02-13',
        0
    ),
    (
        'adresse2@outlook.com',
        'Pascale',
        'Girard',
        'motDePasse',
        '1997-09-06',
        0
    ),
    (
        'adresse@outlook.com',
        'Jean',
        'Boyer',
        'motDePasse',
        '1989-11-12',
        1
    );
INSERT INTO Soigner(
        valDuree,
        uniteDuree,
        valFreq,
        uniteFreq,
        doseParPrise,
        datecreation,
        medicament_id,
        utilisateur_id,
        maladie_id
    )
VALUES (
        4,
        0,
        3,
        0,
        1,
        '2022-04-06',
        SELECT id
        FROM medicament
        WHERE nom_medic = 'Glucophage',
            SELECT id
        FROM utilisateur
        WHERE adresse_mail = 'adresse@gmail.com',
            SELECT id
        FROM maladie
        WHERE nom_maladie = 'Diabète'
    ),
    (
        4,
        1,
        3,
        0,
        2,
        '2022-04-04',
        SELECT id
        FROM medicament
        WHERE nom_medic = 'Paxlovid',
            SELECT id
        FROM utilisateur
        WHERE adresse_mail = 'adresse2@hotmail.fr',
            SELECT id
        FROM maladie
        WHERE nom_maladie = 'Covid-19'
    ),
    (
        4,
        2,
        1,
        1,
        1,
        '2022-04-07',
        SELECT id
        FROM medicament
        WHERE nom_medic = 'Spironolactone',
            SELECT id
        FROM utilisateur
        WHERE adresse_mail = 'adresse2@hotmail.fr',
            SELECT id
        FROM maladie
        WHERE nom_maladie = 'Hypertension artérielle'
    ),
    (
        4,
        2,
        1,
        1,
        1,
        '2022-04-07',
        SELECT id
        FROM medicament
        WHERE nom_medic = 'Paracétamol',
            SELECT id
        FROM utilisateur
        WHERE adresse_mail = 'adresse2@hotmail.fr',
            SELECT id
        FROM maladie
        WHERE nom_maladie = 'Arthrose'
    ),
    (
        4,
        2,
        1,
        1,
        1,
        '2022-04-07',
        SELECT id
        FROM medicament
        WHERE nom_medic = 'Spironolactone',
            SELECT id
        FROM utilisateur
        WHERE adresse_mail = 'adresse2@outlook.com',
            SELECT id
        FROM maladie
        WHERE nom_maladie = 'Hypertension artérielle'
    );
--(4, 3, 2, 2, 1,' 2023 -03 -05 ', SELECT id FROM medicament WHERE nom_medic = ' Maxxxxxxxilase ', SELECT id FROM utilisateur WHERE adresse_mail = ' adresse @mail ', SELECT id FROM maladie WHERE nom_maladie = ' Diaaaaabete '); 
INSERT INTO Toucher(maladie_id, corps_id)
VALUES (
        SELECT id
        FROM Maladie
        WHERE nom_maladie = 'Diabète',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'pancréas'
    ),
    (
        SELECT id
        FROM Maladie
        WHERE nom_maladie = 'Hypertension artérielle',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'reins'
    ),
    (
        SELECT id
        FROM Maladie
        WHERE nom_maladie = 'Arthrose',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'genou'
    ),
    (
        SELECT id
        FROM Maladie
        WHERE nom_maladie = 'Covid-19',
            SELECT id
        FROM Corps
        WHERE nom_partie = 'poumons'
    );

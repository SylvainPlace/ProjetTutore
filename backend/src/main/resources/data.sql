-- Initialisation des tables
INSERT INTO Corps(nom_partie) VALUES
    ('partie1'), -- Les clés sont auto-générées
    ('partie2');

--INSERT INTO Corps(nom_partie_compose_id, nom_partie_est_composee_id) VALUES
  --  (SELECT id FROM Corps WHERE nom_partie = 'partie1', SELECT id FROM Corps WHERE nom_partie = 'partie2');
   
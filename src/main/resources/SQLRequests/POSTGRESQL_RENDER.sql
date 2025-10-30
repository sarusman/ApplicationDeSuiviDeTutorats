-- PostgreSQL
INSERT INTO entreprise (id, nom, raison_sociale, adresse, infos_acces) VALUES
  (1, 'Capgemini', 'Capgemini SE', '11 rue de Tilsitt, 75017 Paris', 'Accès badge accueil'),
  (2, 'Airbus', 'Airbus SAS', '1 rond-point Maurice Bellonte, 31700 Blagnac', 'Bâtiment B, 2e étage'),
  (3, 'Orange', 'Orange SA', '78 rue Olivier de Serres, 75015 Paris', 'Enregistrer à la sécurité')
ON CONFLICT (id) DO NOTHING;

INSERT INTO apprenti (id, nom, prenom, adresse_electronique, telephone, programme, entreprise_id, tuteur_pedagogique_id) VALUES
  (201, 'Martin',  'Lucie', 'lucie.martin@example.com',  '0600000001', 'I1', 1, 101),
  (202, 'Durand',  'Théo',  'theo.durand@example.com',   '0600000002', 'I2', 2, 101),
  (203, 'Bernard', 'Lina',  'lina.bernard@example.com',  '0600000003', 'I3', 3, 102)
ON CONFLICT (id) DO NOTHING;

INSERT INTO visite (id, date, commentaire, apprenti_id) VALUES
  (301, DATE '2025-03-15', 'Bilan intermédiaire', 201),
  (302, DATE '2025-04-10', 'Suivi technique',     202),
  (303, DATE '2025-05-20', 'Préparation soutenance', 203)
ON CONFLICT (id) DO NOTHING;

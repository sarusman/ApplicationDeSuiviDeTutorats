use base_altn72;

INSERT INTO annee_academique (commentaire, date_debut, date_fin)
VALUES ('Première année', '2019-09-20', '2020-07-04'),
       ('Second année', '2020-09-28', '2021-07-02'),
       ('Troisième année', '2021-09-26', '2022-07-03');

INSERT INTO entreprise (adresse, infos_acces, raison_sociale)
VALUES ('35 rue des mirabelles', 'tout', 'Pie'),
       ('12 chm des saint-jacques', 'patout', 'Pi'),
       ('48 boulevar auguste un', 'rien', 'Paille');

INSERT INTO tuteur_entreprise (adresse_electronique, nom, poste, prenom, remarques, telephone, entreprise_id)
VALUES ('jean.jack@Pie.fr', 'Jack', 'ingenieur', 'Jean', 'Jean nai marre', '0612188546', 1),
       ('pierre.lafonte@Pie.fr', 'Lafonte', 'marketeu', 'Pierre', 'Lafonte des glaces', '0612678489', 2),
       ('henri.gole@Pie.fr', 'Gole', 'humoriste', 'Henri', 'Il Henri gole', '0612597568', 3),

       ('una.retiste@Pi.fr', 'Retiste', 'comptable', 'Una', 'Jaurai voulu etre Una Retiste', '0612789456', 2),
       ('marc.ose@Pi.fr', 'Ose', 'chef de projet', 'Marc', 'Il Ose Marc qui quête', '0612896578', 2),
       ('claire.ment@Pi.fr', 'Ment', 'analyste', 'Claire', 'Très Claire Ment', '0612998574', 2),

       ('luc.arn@Paille.fr', 'Arn', 'architecte', 'Luc', 'Dans la Luc Arn', '0612114789', 3),
       ('sarah.pas@Paille.fr', 'Pas', 'développeuse', 'Sarah', 'Sarah croche Pas', '0612236587', 3),
       ('yan.apas@Paille.fr', 'Apas', 'designer', 'Yan', 'Malheureusement Yan Apas', '0612359874', 3);

INSERT INTO apprenti (adresse_electronique, nom, prenom, telephone, etat)
VALUES ('anna.conda@efrei.fr', 'Conda', 'Anna', '0612000001', 'ACTIF'),
       ('luc.ture@efrei.fr', 'Ture', 'Luc', '0612000002', 'ACTIF'),
       ('claire.voyant@efrei.fr', 'Voyant', 'Claire', '0612000003', 'ACTIF'),
       ('alain.vers@efrei.fr', 'Vers', 'Alain', '0612000004', 'ACTIF'),
       ('marc.heur@efrei.fr', 'Heur', 'Marc', '0612000005', 'ACTIF'),
       ('justine.time@efrei.fr', 'Time', 'Justine', '0612000006', 'ACTIF'),
       ('max.imum@efrei.fr', 'Imum', 'Max', '0612000007', 'ACTIF'),
       ('nina.tendo@efrei.fr', 'Tendo', 'Nina', '0612000008', 'ACTIF'),
       ('paul.aire@efrei.fr', 'Aire', 'Paul', '0612000009', 'ACTIF');

INSERT INTO annee_alternance (programme, apprenti_id, annee_academique, entreprise_id, tuteur_entreprise_id, tuteur_pedagogique_id)
VALUES (0, 1, 1, 1, 1, 1),
       (0, 2, 2, 2, 2, 1),
       (0, 3, 3, 3, 3, 1),
       (0, 4, 1, 1, 4, 1),
       (0, 5, 1, 2, 5, 1),
       (0, 6, 1, 3, 6, 1),
       (1, 4, 2, 1, 4, 1),
       (1, 5, 2, 2, 5, 1),
       (1, 6, 2, 3, 6, 1),
       (0, 7, 1, 1, 7, 1),
       (0, 8, 1, 2, 8, 1),
       (0, 9, 1, 3, 9, 1),
       (1, 7, 2, 1, 7, 1),
       (1, 8, 2, 2, 8, 1),
       (1, 9, 2, 3, 9, 1),
       (2, 7, 3, 1, 7, 1),
       (2, 8, 3, 2, 8, 1),
       (2, 9, 3, 3, 9, 1);

INSERT INTO visite (commentaires, date, format, annee_alternance_id)
VALUES
-- === ANNEE_ACADEMIQUE 1 (2019-09-20 → 2020-07-04) ===
('Bonne première rencontre.', '2019-10-10 10:00:00', 'PRESENTIEL', 1),
('Suivi de mi-parcours.', '2020-02-15 14:00:00', 'VISIO', 1),
('Visite finale.', '2020-06-20 09:30:00', 'PRESENTIEL', 1),

('Échange initial positif.', '2019-10-12 09:30:00', 'VISIO', 4),
('Bon suivi à mi-année.', '2020-03-01 10:00:00', 'VISIO', 4),
('Clôture d’année.', '2020-06-15 14:30:00', 'PRESENTIEL', 4),

('Prise de contact réussie.', '2019-10-05 09:00:00', 'PRESENTIEL', 5),
('Bon suivi du projet.', '2020-02-20 11:00:00', 'VISIO', 5),
('Dernière visite.', '2020-06-25 13:00:00', 'VISIO', 5),

('Visite d’intégration.', '2019-10-02 14:00:00', 'PRESENTIEL', 6),
('Point technique.', '2020-03-10 10:00:00', 'VISIO', 6),
('Visite finale.', '2020-06-18 09:00:00', 'VISIO', 6),

('Première rencontre.', '2019-10-01 09:00:00', 'PRESENTIEL', 10),
('Suivi du semestre.', '2020-02-28 14:30:00', 'VISIO', 10),
('Clôture prévue.', '2020-06-26 10:00:00', 'PRESENTIEL', 10),

('Visite initiale.', '2019-10-09 10:00:00', 'PRESENTIEL', 11),
('Point d’étape.', '2020-02-22 11:30:00', 'VISIO', 11),
('Dernière visite.', '2020-06-30 15:00:00', 'VISIO', 11),

('Bonne intégration.', '2019-10-04 09:00:00', 'PRESENTIEL', 12),
('Suivi de progression.', '2020-03-05 13:30:00', 'VISIO', 12),
('Visite de fin.', '2020-06-29 09:30:00', 'VISIO', 12),

('Très bon démarrage.', '2019-09-30 14:00:00', 'PRESENTIEL', 7),
('Bilan mi-parcours.', '2020-02-25 09:30:00', 'VISIO', 7),
('Visite finale.', '2020-06-20 11:00:00', 'VISIO', 7),

('Rencontre initiale.', '2019-09-25 15:00:00', 'PRESENTIEL', 8),
('Suivi régulier.', '2020-03-03 10:00:00', 'VISIO', 8),
('Dernière visite.', '2020-06-28 13:00:00', 'VISIO', 8),

('Entretien de lancement.', '2019-09-26 09:00:00', 'PRESENTIEL', 9),
('Suivi de mi-année.', '2020-02-18 14:00:00', 'VISIO', 9),
('Clôture de stage.', '2020-07-01 09:00:00', 'VISIO', 9),

-- === ANNEE_ACADEMIQUE 2 (2020-09-28 → 2021-07-02) ===
('Rencontre d’ouverture.', '2020-10-05 09:00:00', 'PRESENTIEL', 2),
('Suivi technique.', '2021-02-20 11:00:00', 'VISIO', 2),
('Visite finale.', '2021-06-25 10:30:00', 'VISIO', 2),

('Début prometteur.', '2020-10-10 10:00:00', 'PRESENTIEL', 13),
('Suivi projet.', '2021-03-01 09:00:00', 'VISIO', 13),
('Visite finale.', '2021-06-28 14:00:00', 'VISIO', 13),

('Très bon échange.', '2020-10-12 14:00:00', 'PRESENTIEL', 14),
('Avancement satisfaisant.', '2021-02-25 10:30:00', 'VISIO', 14),
('Dernière visite.', '2021-06-30 09:00:00', 'VISIO', 14),

('Bonne implication.', '2020-10-08 09:00:00', 'PRESENTIEL', 15),
('Suivi régulier.', '2021-03-05 11:00:00', 'VISIO', 15),
('Clôture prévue.', '2021-06-20 13:00:00', 'VISIO', 15),

('Très motivé.', '2020-10-03 13:30:00', 'PRESENTIEL', 8),
('Progrès visibles.', '2021-02-19 09:00:00', 'VISIO', 8),
('Dernier suivi.', '2021-06-25 15:00:00', 'PRESENTIEL', 8),

('Visite initiale réussie.', '2020-09-30 10:30:00', 'PRESENTIEL', 9),
('Suivi de parcours.', '2021-03-03 10:00:00', 'VISIO', 9),
('Clôture finale.', '2021-06-28 14:00:00', 'VISIO', 9),

('Début d’année efficace.', '2020-10-06 10:00:00', 'PRESENTIEL', 4),
('Suivi de mi-année.', '2021-02-23 09:30:00', 'VISIO', 4),
('Dernière visite.', '2021-06-29 11:00:00', 'PRESENTIEL', 4),

('Rencontre initiale.', '2020-10-02 09:00:00', 'PRESENTIEL', 5),
('Bon suivi.', '2021-03-02 14:00:00', 'VISIO', 5),
('Clôture de l’année.', '2021-06-26 09:30:00', 'VISIO', 5),

('Prise de contact.', '2020-09-29 14:00:00', 'PRESENTIEL', 6),
('Suivi intermédiaire.', '2021-02-21 10:00:00', 'VISIO', 6),
('Dernière visite.', '2021-06-27 13:30:00', 'VISIO', 6),

-- === ANNEE_ACADEMIQUE 3 (2021-09-26 → 2022-07-03) ===
('Début d’année prometteur.', '2021-10-05 09:30:00', 'PRESENTIEL', 16),
('Suivi de progression.', '2022-02-15 14:00:00', 'VISIO', 16),
('Visite finale.', '2022-06-28 10:00:00', 'VISIO', 16),

('Bonne dynamique.', '2021-10-10 10:00:00', 'PRESENTIEL', 17),
('Évaluation de mi-parcours.', '2022-03-01 09:00:00', 'VISIO', 17),
('Dernière visite.', '2022-06-25 11:00:00', 'PRESENTIEL', 17),

('Visite d’ouverture.', '2021-10-02 10:30:00', 'PRESENTIEL', 15),
('Suivi semestriel.', '2022-02-20 11:30:00', 'VISIO', 15),
('Clôture finale.', '2022-06-30 09:30:00', 'VISIO', 15);





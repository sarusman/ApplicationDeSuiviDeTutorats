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

SELECT aa.* FROM annee_alternance aa
JOIN apprenti a ON aa.apprenti_id = a.id
WHERE a.etat = 'ACTIF'
  AND (aa.apprenti_id, aa.annee_academique) IN (
    SELECT aa_sub.apprenti_id, MAX(aa_sub.annee_academique)
    FROM annee_alternance aa_sub
             JOIN apprenti a_sub ON aa_sub.apprenti_id = a_sub.id
    WHERE a_sub.etat = 'ACTIF'
    GROUP BY aa_sub.apprenti_id)


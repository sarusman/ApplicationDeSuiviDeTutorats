SELECT
  a.id, a.nom, a.prenom, a.programme,
  e.nom AS entreprise_nom
FROM apprenti a
LEFT JOIN entreprise e ON e.id = a.entreprise_id
WHERE a.archived = FALSE
  AND a.annee_academique = :annee
  AND (:q IS NULL OR a.nom LIKE :q)
ORDER BY a.nom, a.prenom;

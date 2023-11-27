-- Suppression du contenu de la table personne
DELETE FROM personne;

-- Suppression du contenu de la table utilisateur
DELETE FROM public.utilisateur;

-- Insertion de données d'exemple pour une famille
INSERT INTO personne (id, numero_secu, nom, prenom, date_deces, nationalite, genre, naissance, pere, mere)
VALUES
  -- Enfant
  (1, 123456789, 'Doe', 'John', NULL, 'French', 1, '01/01/1990', NULL, NULL),
  
  -- Parents
  (2, 111111111, 'Doe', 'Peter', NULL, 'French', 1, '01/01/1965', NULL, NULL), -- Père
  (3, 222222222, 'Doe', 'Mary', NULL, 'French', 0, '01/01/1970', NULL, NULL), -- Mère

  -- Grands-parents
  (4, 333333333, 'Doe', 'George', NULL, 'French', 1, '01/01/1940', NULL, NULL), -- Grand-père paternel
  (5, 444444444, 'Doe', 'Alice', NULL, 'French', 0, '01/01/1945', NULL, NULL); -- Grand-mère paternelle

-- Liaison des individus dans la famille
UPDATE personne SET pere = 2, mere = 3 WHERE id = 1; -- Enfant lié aux parents
UPDATE personne SET pere = 4, mere = 5 WHERE id = 2; -- Père de l'enfant liés aux grands-parents de l'enfant

-- Insertion de l'utilisateur lié à la personne 'Enfant'
INSERT INTO public.utilisateur (id, email, id_personne, mdp, visibility_level)
VALUES (1, 'test@test.com', 555555555, 'test', 0);

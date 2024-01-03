-- Suppression du contenu de la table personne
DELETE FROM personne;

-- Suppression du contenu de la table utilisateur
DELETE FROM public.utilisateur;

DELETE FROM public.invitation;

-- Insertion de données d'exemple pour une famille
INSERT INTO personne (id, numero_secu, nom, prenom, date_deces, nationalite, genre, naissance, pere, mere)
VALUES
  -- Enfant
  (1, 123456789, 'Doe', 'John', NULL, 'French', 0, '01/01/1990', 2, 3),
  
  -- Parents
  (2, 111111111, 'Doe', 'Peter', NULL, 'French', 0, '1965-01-01', NULL, NULL), -- Père
  (3, 222222222, 'Doe', 'Mary', NULL, 'French', 1, '1970-01-01', NULL, NULL), -- Mère

  -- Grands-parents
  (4, 333333333, 'Doe', 'George', NULL, 'French', 0, '1940-01-01', NULL, NULL), -- Grand-père paternel
  (5, 444444444, 'Doe', 'Alice', NULL, 'French', 1, '1945-01-01',NULL, NULL); -- Grand-mère paternelle

-- Liaison des individus dans la famille
UPDATE personne SET pere = 2, mere = 3 WHERE id = 1; -- Enfant lié aux parents
UPDATE personne SET pere = 4, mere = 5 WHERE id = 2; -- Père de l'enfant liés aux grands-parents de l'enfant

-- Insertion de deux utilisateurs
INSERT INTO public.utilisateur (id, email, id_personne, mdp, visibility_level)
VALUES (1, 'test@test.com', 1, 'test', 0),
	   (2, 'test@gmail.com', 2, 'test', 0);

-- Insertion d'une invitation entre les deux utilisateurs
INSERT INTO public.invitation (id, id_user, relation, root, target)
VALUES (1, 1, NULL, NULL, 2);

--Personnes
INSERT INTO public.personne (genre, naissance, id, numero_secu, nom, prenom) VALUES (1, '720', 4, 2, 'OUI_GRAND_PERE', 'OUI_GRAND_PERE');
INSERT INTO public.personne (genre, naissance, id, numero_secu, nom, prenom, pere) VALUES (0, '1200', 2, 2, 'OUI_PERE', 'OUI_PERE', 4);
INSERT INTO public.personne (genre, naissance, id, numero_secu, nom, prenom) VALUES (1, '720', 3, 2, 'OUI_MERE', 'OUI_MERE');
INSERT INTO public.personne (genre, naissance, id, numero_secu, nom, prenom, pere) VALUES (1, '720', 5, 2, 'OUI_ONCLE', 'OUI_ONCLE', 4);
INSERT INTO public.personne (genre, naissance, id, numero_secu, nom, prenom, pere, mere) VALUES (1, '720', 1, 2, 'OUI', 'OUI', 2, 3);

--User
INSERT INTO public.utilisateur (id, email, id_personne, mdp, visibility_level) VALUES (1, 'test@test.com', 1, 'oui', 0)


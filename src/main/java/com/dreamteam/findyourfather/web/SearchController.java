package com.dreamteam.findyourfather.web;

import org.springframework.stereotype.Controller;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;

/**
 * Le contrôleur {@code SearchController} gère les requêtes liées à la recherche de personnes
 * dans l'application "Find Your Father".
 *
 * <p>Ce contrôleur utilise l'annotation {@code @Controller} pour indiquer qu'il est un composant
 * de contrôleur Spring. Il prend en charge la recherche de personnes et d'utilisateurs.</p>
 *
 * <pre>{@code
 * @Controller
 * public class SearchController {
 *     // ...
 * }
 * }</pre>
 *
 * <p>Assurez-vous que les annotations et les dépendances nécessaires sont correctement
 * configurées pour une utilisation avec Spring Framework.</p>
 * 
 * @author MARTIN MACE DE GASTINES
 * @author LOUIS-ALEXANDRE LAGUET
 * @author ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see Controller
 * @see UtilisateurRepository
 * @see PersonneRepository
 */
@Controller
public class SearchController {
    @SuppressWarnings("unused")
    private final UtilisateurRepository userRep;
    @SuppressWarnings("unused")
    private final PersonneRepository personneRep;

    /**
     * Constructeur du contrôleur de recherche.
     *
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
    public SearchController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.userRep = utilisateurRepository;
        this.personneRep = personneRepository;
    }
}


package com.dreamteam.findyourfather.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;

/**
 * Le contrôleur {@code TreeController} gère les requêtes liées à l'affichage
 * de l'arbre généalogique d'un utilisateur dans l'application "Find Your Father".
 *
 * <p>Ce contrôleur utilise l'annotation {@code @Controller} pour indiquer qu'il est un composant
 * de contrôleur Spring. Il prend en charge la récupération de l'arbre généalogique de l'utilisateur actuellement connecté.</p>
 *
 * <pre>{@code
 * @Controller
 * public class TreeController {
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
 * @see Utilisateur
 * @see Personne
 */
@Controller
public class TreeController {
    private final PersonneRepository personneRepository;
    private final UtilisateurRepository utilisateurRepository;

    /**
     * Constructeur du contrôleur d'affichage de l'arbre généalogique.
     *
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
    public TreeController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Récupère et renvoie l'arbre généalogique de l'utilisateur actuellement connecté.
     *
     * @param session La session HTTP.
     * @return Un ensemble de personnes représentant l'arbre généalogique de l'utilisateur.
     */
    @PostMapping(path = "/showTree", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Set<Personne> familyTree(HttpSession session){
        Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
        Personne personne = personneRepository.findById(user.getIdPersonne()).get();
        Set<Personne> pers = new HashSet<Personne>();

        HashSet<Personne> toSearch = new HashSet<Personne>();
        toSearch.add(personne);

        while(!toSearch.isEmpty()) {
            HashSet<Personne> tmp = new HashSet<Personne>();
            for(Personne p : toSearch) {
                if(p.mere != null) {
                    tmp.add(personneRepository.findById(p.mere).get());
                }
                if(p.pere != null) {
                    tmp.add(personneRepository.findById(p.pere).get());
                }
                tmp.addAll(personneRepository.findByMother(p.getId()));
                tmp.addAll(personneRepository.findByFather(p.getId()));
            }
            tmp.removeAll(pers);
            pers.addAll(tmp);
            toSearch = tmp;
        }
        return pers;
    }
}

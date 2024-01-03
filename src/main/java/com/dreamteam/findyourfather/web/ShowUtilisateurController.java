package com.dreamteam.findyourfather.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;

/**
 * Le contrôleur {@code ShowUtilisateurController} gère les requêtes liées à l'affichage
 * et à la modification des informations de l'utilisateur dans l'application "Find Your Father".
 *
 * <p>Ce contrôleur utilise l'annotation {@code @Controller} pour indiquer qu'il est un composant
 * de contrôleur Spring. Il prend en charge l'affichage des informations de l'utilisateur et de la personne associée,
 * ainsi que la modification de ces informations.</p>
 *
 * <pre>{@code
 * @Controller
 * public class ShowUtilisateurController {
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
public class ShowUtilisateurController {
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;

    /**
     * Constructeur du contrôleur d'affichage des utilisateurs.
     *
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
    public ShowUtilisateurController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.personneRepository = personneRepository;
    }

    /**
     * Récupère et renvoie les informations de l'utilisateur actuellement connecté.
     *
     * @param session La session HTTP.
     * @return L'objet {@code Utilisateur} représentant l'utilisateur.
     */
    @PostMapping(path = "/showUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Utilisateur getUser(HttpSession session){
        if (session.getAttribute("user") == null)
            return null;
        Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
        return user;
    }

    /**
     * Récupère et renvoie les informations de la personne associée à l'utilisateur actuellement connecté.
     *
     * @param session La session HTTP.
     * @return L'objet {@code Personne} représentant la personne associée à l'utilisateur.
     */
    @PostMapping(path = "/showPers", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Personne getPersonne(HttpSession session){
        if (session.getAttribute("user") == null)
            return null;
        Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
        Personne pers = personneRepository.findById(user.getIdPersonne()).get();
        return pers;
    }

    /**
     * Enregistre les modifications apportées aux informations de l'utilisateur actuellement connecté.
     *
     * @param session     La session HTTP.
     * @param email       Le nouvel email de l'utilisateur.
     * @param phoneNumber Le nouveau numéro de téléphone de l'utilisateur.
     */
    @PostMapping(path = "/saveUser")
    public @ResponseBody void saveUser(HttpSession session, @RequestParam String email, @RequestParam Long phoneNumber){
        Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        utilisateurRepository.save(user);
    }

    /**
     * Enregistre les modifications apportées aux informations de la personne associée à l'utilisateur actuellement connecté.
     *
     * @param session     La session HTTP.
     * @param genre       Le nouveau genre de la personne (homme ou femme).
     * @param nationalite La nouvelle nationalité de la personne.
     */
    @PostMapping(path = "/savePers")
    public @ResponseBody void savePers(HttpSession session, @RequestParam String genre, @RequestParam String nationalite){
        Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
        Personne pers = personneRepository.findById(user.getIdPersonne()).get();
        pers.setGenre(genre.toLowerCase().equals("female") ? Personne.Genre.FEMME : Personne.Genre.HOMME);
        pers.setNationalite(nationalite);
        personneRepository.save(pers);
    }
}

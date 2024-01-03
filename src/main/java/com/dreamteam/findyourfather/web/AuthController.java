package com.dreamteam.findyourfather.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Personne.Genre;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;

/**
 * Le contrôleur {@code AuthController} gère les requêtes liées à l'authentification
 * et à l'inscription des utilisateurs dans le cadre de l'application "Find Your Father".
 * 
 * <p>Ce contrôleur utilise l'annotation {@code @Controller} pour indiquer qu'il est un composant
 * de contrôleur Spring. Il expose des méthodes pour l'enregistrement, la connexion et d'autres
 * fonctionnalités liées à l'authentification des utilisateurs.</p>
 *
 * <pre>{@code
 * @Controller
 * public class AuthController {
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
 * @see PostMapping
 * @see ResponseBody
 * @see UtilisateurRepository
 * @see PersonneRepository
 * @see Personne
 * @see Genre
 * @see HttpSession
 */
@Controller
public class AuthController {
	
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;
	    
	/**
     * La classe interne {@code LoginInfo} représente les informations nécessaires pour la connexion
     * ou l'inscription d'un utilisateur dans le cadre de l'application "Find Your Father".
     * 
     * <p>Cette classe encapsule les détails tels que l'adresse e-mail, le mot de passe, le numéro
     * de sécurité sociale, le nom, le prénom, la date de naissance, la nationalité et le genre.</p>
     * 
     * <pre>{@code
     * private class LoginInfo {
     *     // ... (attributs de la classe)
     * }
     * }</pre>
     * 
     * @author MARTIN MACE DE GASTINES
     * @author LOUIS-ALEXANDRE LAGUET
     * @author ALEXIS TOURRENC--LECERF
     * @version 1.0
     * @since 2024-01-03
     */
    @SuppressWarnings("unused")
    private class LoginInfo {
        private String email;
		private String password;
		private Long ssn;
		private String lastname;
		private String firstname;
		private String birthdate;
		private String nationality;
		private String gender;
        /**
         * L'adresse e-mail de l'utilisateur.
         *
         * @return L'adresse e-mail.
         */
        public String getEmail() {
            return email;
        }

        /**
         * Le mot de passe de l'utilisateur.
         *
         * @return Le mot de passe.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Le numéro de sécurité sociale de l'utilisateur.
         *
         * @return Le numéro de sécurité sociale.
         */
        public Long getSsn() {
            return ssn;
        }

        /**
         * Le nom de l'utilisateur.
         *
         * @return Le nom.
         */
        public String getLastname() {
            return lastname;
        }

        /**
         * Le prénom de l'utilisateur.
         *
         * @return Le prénom.
         */
        public String getFirstname() {
            return firstname;
        }

        /**
         * La date de naissance de l'utilisateur.
         *
         * @return La date de naissance.
         */
        public String getBirthdate() {
            return birthdate;
        }

        /**
         * La nationalité de l'utilisateur.
         *
         * @return La nationalité.
         */
        public String getNationality() {
            return nationality;
        }

        /**
         * Le genre de l'utilisateur.
         *
         * @return Le genre.
         */
        public String getGender() {
            return gender;
        }
    }

	
	/**
     * Constructeur du contrôleur d'authentification.
     *
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
	public AuthController(UtilisateurRepository utilisateurRepository,PersonneRepository personneRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.personneRepository = personneRepository;
    }

	/**
     * Gère la requête d'enregistrement d'un nouvel utilisateur.
     *
     * @param email         L'adresse e-mail de l'utilisateur.
     * @param password      Le mot de passe de l'utilisateur.
     * @param phoneNumber   Le numéro de téléphone de l'utilisateur.
     * @param ssn           Le numéro de sécurité sociale de l'utilisateur.
     * @param lastname      Le nom de l'utilisateur.
     * @param firstname     Le prénom de l'utilisateur.
     * @param birthdate     La date de naissance de l'utilisateur.
     * @param nationality   La nationalité de l'utilisateur.
     * @param gender        Le genre de l'utilisateur.
     * @param session       La session HTTP.
     * @return              Un message indiquant le résultat de l'enregistrement.
     */
    @PostMapping(path="/register",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String register(@RequestParam String email,
    		@RequestParam String password,
    		@RequestParam Long phoneNumber,
    		@RequestParam Long ssn,
    		@RequestParam String lastname,
    		@RequestParam String firstname,
    		@RequestParam String birthdate,
    		@RequestParam String nationality,
    		@RequestParam String gender, 
    		HttpSession session) {
    	
    	if(utilisateurRepository.findByEmail(email).size()==0){
    		Personne pers = searchPersonne(lastname, firstname, birthdate);
    		if (pers == null){
    			pers = new Personne(ssn,lastname,firstname);
    			pers.setNationalite(nationality);
        		
        		if(gender=="male") {
        			pers.setGenre(Genre.HOMME);
        		}
        		else {
        			pers.setGenre(Genre.FEMME);
        		}
        		pers.setNaissance(birthdate);
        		personneRepository.save(pers);
        		Utilisateur utilisateur = new Utilisateur(pers.getId(), phoneNumber, email, password, Utilisateur.Visiblity.PUBLIC);
        		utilisateurRepository.save(utilisateur);
        		return "<p style='color: green;'>Account successfully created</p>";
    		}
            return "exists";
    	}
    	return "<p style='color: red;'>This email is already used</p>";
    }

	/**
     * Gère la requête de connexion d'un utilisateur.
     *
     * @param session   La session HTTP.
     * @param email     L'adresse e-mail de l'utilisateur.
     * @param password  Le mot de passe de l'utilisateur.
     * @return          Un message indiquant le résultat de la connexion.
     */
    @PostMapping("/login")
    public @ResponseBody String login(HttpSession session,@RequestParam String email,
    		@RequestParam String password) {
    	List<Utilisateur> utilisateurs = utilisateurRepository.findByEmail(email);
    	if(utilisateurs.size()>0) {
    		Utilisateur utilisateur = utilisateurs.get(0);
    		if(utilisateur.getMdp().equals(password)) {
    			session.setAttribute("user", utilisateur.getId());
            	return "<p style='color: green;'>You are now logged as '"+utilisateur.getEmail()+"'.</p>";
    		}
    	}
    	return "<p style='color: red;'>The email/password combination is incorrect.</p>";
    }

	/**
     * Gère la requête d'enregistrement d'un utilisateur correspondante à une personne déjà existante.
     *
     * @param email         L'adresse e-mail de l'utilisateur.
     * @param password      Le mot de passe de l'utilisateur.
     * @param phoneNumber   Le numéro de téléphone de l'utilisateur.
     * @param ssn           Le numéro de sécurité sociale de l'utilisateur.
     * @param lastname      Le nom de l'utilisateur.
     * @param firstname     Le prénom de l'utilisateur.
     * @param birthdate     La date de naissance de l'utilisateur.
     * @param nationality   La nationalité de l'utilisateur.
     * @param gender        Le genre de l'utilisateur.
     * @param session       La session HTTP.
     * @return              Un message indiquant le résultat de l'enregistrement.
     */
    @PostMapping(path="/registerAlreadyExisted",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String registerAlreadyExisted(@RequestParam String email,
    		@RequestParam String password,
    		@RequestParam Long phoneNumber,
    		@RequestParam Long ssn,
    		@RequestParam String lastname,
    		@RequestParam String firstname,
    		@RequestParam String birthdate,
    		@RequestParam String nationality,
    		@RequestParam String gender, 
    		HttpSession session) {
    	if(utilisateurRepository.findByEmail(email).size()==0){
    		Personne pers = searchPersonne(lastname, firstname, birthdate);
    		Utilisateur utilisateur = new Utilisateur(pers.getId(), phoneNumber, email, password, Utilisateur.Visiblity.PUBLIC);
            utilisateurRepository.save(utilisateur);
            session.setAttribute("id", pers.getId());
            return "<p style='color: green;'>Account successfully created</p>";
            
    	}
    	return "<p style='color: red;'>This email is already used</p>";
    }

	/**
     * Recherche une personne dans le référentiel en fonction du nom, du prénom et de la date de naissance.
     *
     * @param nom           Le nom de la personne.
     * @param prenom        Le prénom de la personne.
     * @param naisssance    La date de naissance de la personne.
     * @return              La personne correspondante ou {@code null} si aucune correspondance n'est trouvée.
     */
    private Personne searchPersonne(String nom, String prenom, String naisssance) {
		
		try {
			Personne pers = personneRepository.findByName(nom, prenom, naisssance);
			return pers;
		}catch(Exception e) {
			return null;
		}
	}
}


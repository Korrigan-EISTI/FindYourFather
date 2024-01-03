package com.dreamteam.findyourfather.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * Le contrôleur {@code RelationController} gère les requêtes liées aux relations entre les personnes
 * dans l'application "Find Your Father".
 *
 * <p>Ce contrôleur utilise l'annotation {@code @RestController} pour indiquer qu'il est un composant
 * de contrôleur Spring qui traite les requêtes REST. Il expose des méthodes pour ajouter, accepter, refuser
 * et supprimer des relations entre les personnes.</p>
 *
 * <pre>{@code
 * @RestController
 * @RequestMapping("/relation")
 * public class RelationController {
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
 * @see RestController
 * @see RequestMapping
 * @see PostMapping
 * @see RequestParam
 * @see HttpSession
 * @see InvitationRepository
 * @see UtilisateurRepository
 * @see PersonneRepository
 * @see Invitation
 * @see Personne
 */
@RestController
@RequestMapping("/relation")
public class RelationController {

    private final InvitationRepository invitationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;

	/**
     * Constructeur du contrôleur de relations.
     *
     * @param invitationRepository  Le référentiel des invitations.
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
    public RelationController(InvitationRepository invitationRepository, UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.invitationRepository = invitationRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.personneRepository = personneRepository;
    }

	/**
     * Ajoute une relation entre deux personnes.
     *
     * @param relationToAdd Le type de relation à ajouter (père, mère, enfant).
     * @param lastName      Le nom de la personne à ajouter.
     * @param firstName     Le prénom de la personne à ajouter.
     * @param birthdate     La date de naissance de la personne à ajouter.
     * @param ssn           Le numéro de sécurité sociale de la personne à ajouter.
     * @param nationality   La nationalité de la personne à ajouter.
     * @param gender        Le genre de la personne à ajouter.
     * @param id            L'ID de la personne existante à laquelle ajouter la relation.
     * @param session       La session HTTP.
     * @return              Le message indiquant le succès ou l'échec de l'opération.
     */
    @PostMapping(path = "/add",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String addRelation(@RequestParam String relationToAdd,
    		@RequestParam String lastName,
    		@RequestParam String firstName,
    		@RequestParam String birthdate,
			@RequestParam Long ssn,
    		@RequestParam String nationality,
    		@RequestParam String gender,
    		@RequestParam Long id,
    		HttpSession session) {
    	
    	Personne personne = personneRepository.getReferenceById(id);
    	Long target;
    	
    	//Checking already existing parental links before adding a parental relation
    	if(relationToAdd.equals("father") && personne.pere != null) return "This person already has a father";
    	else if(relationToAdd.equals("mother") && personne.mere != null) return "This person already has a mother";
    	
    	List<Personne> targets = personneRepository.findByNumeroSecu(ssn);
    	if(targets.size()>0) {
			target = targets.get(0).getId();
		}
		else {
			Personne p = personneRepository.save(new Personne(ssn,lastName,firstName));
			p.setNaissance(birthdate);
			p.setNationalite(nationality);
			if(gender.equals("female")) {
				p.setGenre(Personne.Genre.FEMME);
			}
			else {
				p.setGenre(Personne.Genre.HOMME);
			}
			target = p.getId();
			personneRepository.save(p);
		}
    	
    	targets = personneRepository.findByNumeroSecu(ssn);
		List<Utilisateur> utilisateurs = utilisateurRepository.findByIdPersonne(target);
		if(utilisateurs.size()==0) {
	    	if(relationToAdd.equals("father")) {
				personne.setPere(target);
				personneRepository.save(personne);
	    	}
	    	else if(relationToAdd.equals("mother")){
	    		personne.setMere(target);
				personneRepository.save(personne);
	    	}
	    	else if(relationToAdd.equals("child")) {
	    		Personne child = targets.get(0);
	    		if(personne.getGenre().equals(Personne.Genre.HOMME)) {
	    			child.setPere(id);
	    			personneRepository.save(child);
	    		}
	    		else if(personne.getGenre().equals(Personne.Genre.FEMME)) {
	    			child.setMere(id);
	    			personneRepository.save(child);
	    		}
	    	}
			return "Relation successfully created";
		}
		else {
			invitationRepository.save(new Invitation((Long)session.getAttribute("user"),id,target,relationToAdd));
			return "Invitation sent";
		}
    }
    
	/**
     * Accepte une invitation de relation entre deux personnes.
     *
     * @param id      L'ID de l'invitation à accepter.
     * @param session La session HTTP.
     * @return        Le message indiquant le succès ou l'échec de l'opération.
     */
    @PostMapping(path = "/remove",produces = MediaType.TEXT_PLAIN_VALUE)
    public String refuseRelation(@RequestParam Long id, @RequestParam String relation, HttpSession session) {
    	
    	Personne p = personneRepository.getReferenceById(id);
    	switch(relation) {
	    	case "father":
	    		p.setPere(null);
	    		personneRepository.save(p);
	    		return "Removed father";
	    		
	    	case "mother":
	    		p.setMere(null);
	    		personneRepository.save(p);
	    		return "Removed mother";
	    }
    	return "Wrong relation specified";
    }

	/**
     * Accepte une invitation de relation entre deux personnes.
     *
     * @param id      L'ID de l'invitation à accepter.
     * @param session La session HTTP.
     * @return        Le message indiquant le succès ou l'échec de l'opération.
     */
    @PostMapping(path = "/accept",produces = MediaType.TEXT_PLAIN_VALUE)
    public String acceptRelation(@RequestParam Long id, HttpSession session) {
    	
    	Invitation invitation = invitationRepository.getReferenceById(id);
    	Long user = utilisateurRepository.findByIdPersonne(invitation.getTarget()).get(0).getId();
    	
    	if(!((Long)session.getAttribute("user")).equals(user)) return "Wrong user";
		
    	String relation = invitation.getRelation();
		Personne root = personneRepository.getReferenceById(invitation.getRoot());
		
		if(relation.equals("father")) {
			root.setPere(invitation.getTarget());
			personneRepository.save(root);
    	}
    	else if(relation.equals("mother")){
    		root.setMere(invitation.getTarget());
			personneRepository.save(root);
    	}
    	else if(relation.equals("child")) {
    		Personne target = personneRepository.getReferenceById(invitation.getTarget());
    		if(root.getGenre().equals(Personne.Genre.HOMME)) {
    			target.setPere(id);
    		}
    		if(root.getGenre().equals(Personne.Genre.FEMME)) {
    			target.setMere(id);
    		}
			personneRepository.save(target);
    	}
		invitationRepository.deleteById(id);
    	return "ok";
    }

	/**
     * Refuse une invitation de relation entre deux personnes.
     *
     * @param id      L'ID de l'invitation à refuser.
     * @param session La session HTTP.
     * @return        Le message indiquant le succès ou l'échec de l'opération.
     */
    @PostMapping(path = "/refuse",produces = MediaType.TEXT_PLAIN_VALUE)
    public String refuseRelation(@RequestParam Long id,HttpSession session) {
    	
    	Invitation invitation = invitationRepository.getReferenceById(id);
    	if((Long)session.getAttribute("user") == invitation.getIdUser()) {
    		invitationRepository.deleteById(id);
    	}
		return "ok";
    	
    }
}

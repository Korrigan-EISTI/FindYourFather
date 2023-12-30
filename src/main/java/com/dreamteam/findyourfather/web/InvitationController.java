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

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    private final InvitationRepository invitationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;

    public InvitationController(InvitationRepository invitationRepository, UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.invitationRepository = invitationRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.personneRepository = personneRepository;
    }

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
		}
    	
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
	    		}
	    		if(personne.getGenre().equals(Personne.Genre.FEMME)) {
	    			child.setMere(id);
	    		}
				personneRepository.save(child);
	    		
	    	}
			return "Relation successfully created";
		}
		else {
			invitationRepository.save(new Invitation((Long)session.getAttribute("user"),id,target,relationToAdd,Invitation.Status.PENDING));
			return "Invitation sent";
		}
    }

}

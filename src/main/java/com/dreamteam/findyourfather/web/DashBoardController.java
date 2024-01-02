package com.dreamteam.findyourfather.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Personne;

import jakarta.servlet.http.HttpSession;

@RestController
public class DashBoardController {

    private final InvitationRepository invitationRepository;
    private final UtilisateurRepository utilisateurRepository;
	private final PersonneRepository personneRepository;

    public DashBoardController(InvitationRepository invitationRepository, UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.invitationRepository = invitationRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.personneRepository = personneRepository;
    }
    
    @GetMapping("/getPersonneById/{id}")
    public Optional<Personne> getPersonneById(@PathVariable Long id) {
        return personneRepository.findById(id);
    }
    
	@PostMapping(path = "/getInvitations", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Invitation> getInvitations(HttpSession session){
		return invitationRepository.findByTarget(utilisateurRepository.getReferenceById((Long)session.getAttribute("user")).getIdPersonne());
	}
}
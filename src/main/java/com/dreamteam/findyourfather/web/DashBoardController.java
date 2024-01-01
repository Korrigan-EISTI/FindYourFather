package com.dreamteam.findyourfather.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Invitation;
import jakarta.servlet.http.HttpSession;

@RestController
public class DashBoardController {

    private final InvitationRepository invitationRepository;
    private final UtilisateurRepository utilisateurRepository;
    @SuppressWarnings("unused")
	private final PersonneRepository personneRepository;

    public DashBoardController(InvitationRepository invitationRepository, UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.invitationRepository = invitationRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.personneRepository = personneRepository;
    }
	@PostMapping(path = "/getInvitations", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Invitation> getInvitations(HttpSession session){
		return invitationRepository.findByTarget(utilisateurRepository.getReferenceById((Long)session.getAttribute("user")).getIdPersonne());
	}
}
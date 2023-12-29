package com.dreamteam.findyourfather.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;

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
	@PostMapping(path = "/getInvitations", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Invitation> getInvitations(HttpSession session){
		System.out.println(""+(Long)session.getAttribute("user"));
		return invitationRepository.findByTarget(utilisateurRepository.getReferenceById((Long)session.getAttribute("user")).getIdPersonne());
	}
}
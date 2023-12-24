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

@Controller
public class SearchController {
	private final UtilisateurRepository userRep;
	private final PersonneRepository personneRep;
	
	public SearchController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.userRep = utilisateurRepository;
        this.personneRep = personneRepository;
    }
}

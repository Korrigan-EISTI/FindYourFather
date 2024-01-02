package com.dreamteam.findyourfather.web;

import org.springframework.stereotype.Controller;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;

@Controller
public class SearchController {
	@SuppressWarnings("unused")
	private final UtilisateurRepository userRep;
	@SuppressWarnings("unused")
	private final PersonneRepository personneRep;
	
	public SearchController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.userRep = utilisateurRepository;
        this.personneRep = personneRepository;
    }
}

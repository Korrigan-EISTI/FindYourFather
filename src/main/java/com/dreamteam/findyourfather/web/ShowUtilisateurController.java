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
public class ShowUtilisateurController {
	private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;
    
	public ShowUtilisateurController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.personneRepository = personneRepository;
    }
	
	@PostMapping(path = "/showUser", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody Utilisateur getUser(HttpSession session){
		
		if (session.getAttribute("user") == null)
			return null;
		Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
		return user;
	}
	
	@PostMapping(path = "/showPers", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody Personne getPersonne(HttpSession session){
		
		if (session.getAttribute("user") == null)
			return null;
		
		Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
		Personne pers = personneRepository.findById(user.getIdPersonne()).get();
		return pers;
	}
	
	@PostMapping(path = "/saveUser")
	
	public @ResponseBody void saveUser(HttpSession session, @RequestParam String email, @RequestParam Long phoneNumber){
		Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		utilisateurRepository.save(user);
	}
	
	@PostMapping(path = "/savePers")
	
	public @ResponseBody void savePers(HttpSession session, @RequestParam String genre, @RequestParam String nationalite){
		Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
		Personne pers = personneRepository.findById(user.getIdPersonne()).get();
		pers.setGenre(genre.toLowerCase() == "female" ? Personne.Genre.FEMME : Personne.Genre.HOMME);
		pers.setNationalite(nationalite);
		personneRepository.save(pers);
	}
}

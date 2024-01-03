package com.dreamteam.findyourfather.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;

@Controller
public class TreeController {
    private final PersonneRepository personneRepository;
    private final UtilisateurRepository utilisateurRepository;
    
	public TreeController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
        this.utilisateurRepository = utilisateurRepository;
    }
	
	@PostMapping(path = "/showTree", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<Personne> familyTree(HttpSession session){
		
		Utilisateur user = utilisateurRepository.findById((Long) session.getAttribute("user")).get();
    	Personne personne = personneRepository.findById(user.getIdPersonne()).get();
    	Set<Personne> pers = new HashSet<Personne>();
    	
    	HashSet<Personne> toSearch = new HashSet<Personne>();
    	toSearch.add(personne);
    	
    	while(!toSearch.isEmpty()) {
    		HashSet<Personne> tmp = new HashSet<Personne>();
    		for(Personne p : toSearch) {
    			if(p.mere!=null)
    			{
    				tmp.add(personneRepository.findById(p.mere).get());
    			}
    			if(p.pere!=null)
    			{
    				tmp.add(personneRepository.findById(p.pere).get());
    			}
    			tmp.addAll(personneRepository.findByMother(p.getId()));
    			tmp.addAll(personneRepository.findByFather(p.getId()));
    		}
    		tmp.removeAll(pers);
    		pers.addAll(tmp);
    		toSearch=tmp;
    	}
		return pers;
	}
}

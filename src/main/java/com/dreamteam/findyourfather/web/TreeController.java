package com.dreamteam.findyourfather.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;

import jakarta.servlet.http.HttpSession;

@Controller
public class TreeController {
	private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;
    
	public TreeController(UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.personneRepository = personneRepository;
    }
	
	@PostMapping(path = "/showTree", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<Personne> familyTree(HttpSession session){
    	Personne personne = personneRepository.findById((Long) session.getAttribute("user")).get();
    	
    	Set<Personne> pers = new HashSet<Personne>();
    	
    	getTree(personne, pers);
		return pers;
	}
    
    private void getTree(Personne pers, Set<Personne> p)
    {
    	p.add(pers);
    	
    	if (pers.mere != null)
    	{
    		getTree(personneRepository.findById(pers.mere).get(), p);
    		p.addAll(personneRepository.findByMother(pers.mere));
    	}
    		
    		
    	if( pers.pere != null)
    	{
    		getTree(personneRepository.findById(pers.pere).get(), p);
    		p.addAll(personneRepository.findByFather(pers.pere));
    	}
    		
    }
}

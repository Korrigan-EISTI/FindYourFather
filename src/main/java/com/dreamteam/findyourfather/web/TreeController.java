package com.dreamteam.findyourfather.web;

import java.util.ArrayList;
import java.util.List;

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
	
	public @ResponseBody List<Personne> familyTree(HttpSession session){
    	Personne personne = personneRepository.findById((Long) session.getAttribute("user")).get();
    	
    	List<Personne> pers = new ArrayList<Personne>();
    	
    	getTree(personne, pers);
		return pers;
	}
    
    private void getTree(Personne pers, List<Personne> p)
    {
    	p.add(pers);
    	
    	if (pers.mere != null)
    	{
    		getTree(personneRepository.findById(pers.mere).get(), p);
    		List<Personne> tmpPers = personneRepository.findByMother(pers.mere);
    		for (Personne personne : tmpPers)
    		{
    			if (!p.contains(personne))
    				p.add(personne);
    		}
    	}
    		
    		
    	if( pers.pere != null)
    	{
    		getTree(personneRepository.findById(pers.pere).get(), p);
    		List<Personne> tmpPers = personneRepository.findByFather(pers.pere);
    		for (Personne personne : tmpPers)
    		{
    			if (!p.contains(personne))
    				p.add(personne);
    		}
    	}
    		
    }
}

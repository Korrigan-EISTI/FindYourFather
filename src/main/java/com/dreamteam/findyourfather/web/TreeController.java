package com.dreamteam.findyourfather.web;

import java.util.ArrayList;
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
    	HashSet<Personne> toSearch= new HashSet<Personne>();
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
    		for(Personne p : tmp) {
    			System.out.println(""+p.getId());
    		}
    		pers.addAll(tmp);
    		toSearch=tmp;
    	}
		return pers;
	}
    
    private void getParent(Personne pers, Set<Personne> p) {
    	p.add(pers);
    	
    	if (pers.mere != null)
    	{
    		getParent(personneRepository.findById(pers.mere).get(), p);
    		p.addAll(personneRepository.findByMother(pers.mere));
    	}
    		
    	if( pers.pere != null)
    	{
    		getParent(personneRepository.findById(pers.pere).get(), p);
    		p.addAll(personneRepository.findByFather(pers.pere));
    	}	
    }
    
    private void getChildren(Personne pers, Set<Personne> p){
    	Set<Personne> children;
    	switch(pers.getGenre())
    	{
    		case HOMME : 
    			children = personneRepository.findByFather(pers.getId());
    			p.addAll(children);
    			for (Personne child : children){
    				getChildren(child, p);
    				getParent(child, p);
    			}
    			break;
    		case FEMME :
    			children = personneRepository.findByMother(pers.getId());
    			p.addAll(children);
    			for (Personne child : children){
    				getChildren(child, p);
    				getParent(child, p);
    			}
    			break;
    		default:
    			break;
    	}

    }
}

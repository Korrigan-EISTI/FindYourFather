package com.dreamteam.findyourfather.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Personne.Genre;
import com.dreamteam.findyourfather.entities.Utilisateur;

import jakarta.servlet.http.HttpSession;


@Controller
public class AuthController {
	
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;
    
	private class LoginInfo{
		
		private String email;
		private String password;
		private Long ssn;
		private String lastname;
		private String firstname;
		private String birthdate;
		private String nationality;
		private String gender;
		
		public String getEmail() {
			return email;
		}
		public String getPassword() {
			return password;
		}
		public Long getSsn() {
			return ssn;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getBirthdate() {
			return birthdate;
		}
		public String getNationality() {
			return nationality;
		}
		public String getGender() {
			return gender;
		}		
	}
	
	public AuthController(UtilisateurRepository utilisateurRepository,PersonneRepository personneRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.personneRepository = personneRepository;
    }
	
    @PostMapping(path="/register",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String register(@RequestParam String email,
    		@RequestParam String password,
    		@RequestParam Long ssn,
    		@RequestParam String lastname,
    		@RequestParam String firstname,
    		@RequestParam String birthdate,
    		@RequestParam String nationality,
    		@RequestParam String gender) {
    	System.out.println(email);
    	
    	if(utilisateurRepository.findByEmail(email).size()==0){
    		Personne personne = new Personne(ssn,lastname,firstname);
    		personne.setNationalite(nationality);
    		if(gender=="male") {
    			personne.setGenre(Genre.HOMME);
    		}
    		else {
    			personne.setGenre(Genre.FEMME);
    		}
    		personne.setDateNaissance(birthdate);
    		personneRepository.save(personne);
    		Utilisateur utilisateur = new Utilisateur(null, personne.getId(), email, password, Utilisateur.Visiblity.PUBLIC);
            utilisateurRepository.save(utilisateur);
        	return "<p style='color: green;'>Account successfully created</p>";
    	}
    	return "<p style='color: red;'>This email is already used</p>";
    }
    @PostMapping("/login")
    public @ResponseBody String login(HttpSession session,@RequestParam String email,
    		@RequestParam String password) {
    	List<Utilisateur> utilisateurs = utilisateurRepository.findByEmail(email);
    	if(utilisateurs.size()>0) {
    		Utilisateur utilisateur = utilisateurs.get(0);
    		if(utilisateur.getMdp().equals(password)) {
    			session.setAttribute("user",utilisateur.getId());
            	return "<p style='color: green;'>You are now logged as "+utilisateur.getEmail()+"</p>";
    		}
        	return "<p style='color: red;'>Wrong password</p>";
    	}
    	return "<p style='color: red;'>Wrong email</p>";
    }
}


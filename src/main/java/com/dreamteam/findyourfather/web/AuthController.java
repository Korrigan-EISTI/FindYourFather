package com.dreamteam.findyourfather.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;


@Controller
public class AuthController {
    private final UtilisateurRepository utilisateurRepository;
    private final PersonneRepository personneRepository;
    
	private class LoginInfo{
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
		private String email;
		private String password;
		private Long ssn;
		private String lastname;
		private String firstname;
		private String birthdate;
		private String nationality;
		private String gender;
		
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
    		personneRepository.save(personne);
    		Utilisateur utilisateur = new Utilisateur(null, personne.getId(), email, password, Utilisateur.Visiblity.PUBLIC);
            utilisateurRepository.save(utilisateur);
        	return "success";
    	}
    	return "address already in use";
    }
}


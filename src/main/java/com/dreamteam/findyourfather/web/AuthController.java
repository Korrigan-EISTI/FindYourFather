package com.dreamteam.findyourfather.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Personne;
import com.dreamteam.findyourfather.entities.Utilisateur;


@Controller
public class AuthController {
    private final UtilisateurRepository utilisateurRepository;
    
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
	public AuthController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
	
    @PostMapping("/register")
    public String register(@RequestParam LoginInfo loginInfo) {
    	System.out.println("click");
    	if(utilisateurRepository.findByEmail(loginInfo.getEmail()).size()==0){
        	System.out.println("new");
    		Personne personne = new Personne(loginInfo.getSsn(),loginInfo.getLastname(),loginInfo.getFirstname());
    		Utilisateur utilisateur = new Utilisateur(null, personne.getId(), loginInfo.getEmail(), loginInfo.getPassword(), Utilisateur.Visiblity.PUBLIC);
            return "index.html";
    	}
    	return "index.html";
    }
}


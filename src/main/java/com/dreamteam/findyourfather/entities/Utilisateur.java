package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public enum Visiblity{
		PUBLIC,
		PRIVATE,
		PROTECTED
	}
	
	@Id @GeneratedValue
	private Long id;
	private Long idPersonne;
	private Long phoneNumber;
	private String email;
	private String mdp;
	private Visiblity visibilityLevel;

	public Utilisateur() {
		super();
	}
	
	public Utilisateur(Long idPersonne, Long phoneNumber, String email, String mdp, Visiblity visibilityLevel) {
		super();
		this.idPersonne = idPersonne;
		this.setPhoneNumber(phoneNumber);
		this.email = email;
		this.mdp = mdp;
		this.visibilityLevel = visibilityLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Visiblity getVisibilityLevel() {
		return visibilityLevel;
	}

	public void setVisibilityLevel(Visiblity visibilityLevel) {
		this.visibilityLevel = visibilityLevel;
	}
}
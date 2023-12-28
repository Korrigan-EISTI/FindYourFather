package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Personne implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public enum Genre{
		HOMME,
		FEMME
	}
	
	@Id @GeneratedValue
	private Long id;
	private Long numeroSecu;
	private String nom;
	private String prenom;
	private String naissance;
	private String dateDeces;
	private String nationalite;
	private Genre genre;

	public Long pere;
	public Long mere;
	
	
	public Personne(Long numeroSecu, String nom, String prenom){
		super();
		this.nom = nom;
		this.numeroSecu = numeroSecu;
		this.prenom = prenom;
	}
	
	public Personne() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumeroSecu() {
		return numeroSecu;
	}
	
	public void setNumeroSecu(Long numeroSecu) {
		this.numeroSecu = numeroSecu;
	}
	
	public Long getPere() {
		return pere;
	}

	public void setPere(Long pere) {
		this.pere = pere;
	}

	public Long getMere() {
		return mere;
	}

	public void setMere(Long mere) {
		this.mere = mere;
	}
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getDateDeces() {
		return dateDeces;
	}
	
	public void setDateDeces(String dateDeces) {
		this.dateDeces = dateDeces;
	}
	
	public String getNationalite() {
		return nationalite;
	}
	
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}
}
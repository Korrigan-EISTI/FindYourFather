package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * La classe {@code Personne} représente une entité dans l'application "Find Your Father".
 * Chaque personne peut avoir des caractéristiques telles que le numéro de sécurité sociale, le nom,
 * le prénom, la date de naissance, la date de décès, la nationalité, le genre, le père, et la mère.
 * 
 * <p>La classe utilise l'annotation {@code @Entity} pour indiquer qu'elle est une entité JPA,
 * et elle implémente {@code Serializable} pour permettre la sérialisation de l'objet.</p>
 *
 * <pre>{@code
 * public class Personne implements Serializable {
 *     // ...
 * }
 * }</pre>
 *
 * <p>Assurez-vous que l'annotation {@code @Entity} est correctement configurée pour une utilisation
 * avec votre fournisseur de persistance JPA.</p>
 * 
 * @author MARTIN MACE DE GASTINES
 * @author LOUIS-ALEXANDRE LAGUET
 * @author ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see Serializable
 * @see Entity
 * @see GeneratedValue
 * @see Id
 */
@Entity
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * L'énumération {@code Genre} définit les genres possibles pour une personne.
     * Les valeurs possibles sont HOMME et FEMME.
     */
    public enum Genre {
        HOMME,
        FEMME
    }

    @Id
    @GeneratedValue
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

    /**
     * Constructeur paramétré de la personne.
     *
     * @param numeroSecu Le numéro de sécurité sociale de la personne.
     * @param nom        Le nom de la personne.
     * @param prenom     Le prénom de la personne.
     */
    public Personne(Long numeroSecu, String nom, String prenom) {
        super();
        this.nom = nom;
        this.numeroSecu = numeroSecu;
        this.prenom = prenom;
    }

    /**
     * Constructeur par défaut de la personne.
     */
    public Personne() {
        super();
    }

    /**
     * Obtient l'ID de la personne.
     *
     * @return L'ID de la personne.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'ID de la personne.
     *
     * @param id L'ID de la personne.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le numéro de sécurité sociale de la personne.
     *
     * @return Le numéro de sécurité sociale de la personne.
     */
    public Long getNumeroSecu() {
        return numeroSecu;
    }

    /**
     * Définit le numéro de sécurité sociale de la personne.
     *
     * @param numeroSecu Le numéro de sécurité sociale de la personne.
     */
    public void setNumeroSecu(Long numeroSecu) {
        this.numeroSecu = numeroSecu;
    }

    /**
     * Obtient l'ID du père de la personne.
     *
     * @return L'ID du père de la personne.
     */
    public Long getPere() {
        return pere;
    }

    /**
     * Définit l'ID du père de la personne.
     *
     * @param pere L'ID du père de la personne.
     */
    public void setPere(Long pere) {
        this.pere = pere;
    }

    /**
     * Obtient l'ID de la mère de la personne.
     *
     * @return L'ID de la mère de la personne.
     */
    public Long getMere() {
        return mere;
    }

    /**
     * Définit l'ID de la mère de la personne.
     *
     * @param mere L'ID de la mère de la personne.
     */
    public void setMere(Long mere) {
        this.mere = mere;
    }

    /**
     * Obtient le nom de la personne.
     *
     * @return Le nom de la personne.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la personne.
     *
     * @param nom Le nom de la personne.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom de la personne.
     *
     * @return Le prénom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de la personne.
     *
     * @param prenom Le prénom de la personne.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient la date de naissance de la personne.
     *
     * @return La date de naissance de la personne.
     */
    public String getNaissance() {
        return naissance;
    }

    /**
     * Définit la date de naissance de la personne.
     *
     * @param naissance La date de naissance de la personne.
     */
    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    /**
     * Obtient la date de décès de la personne.
     *
     * @return La date de décès de la personne.
     */
    public String getDateDeces() {
        return dateDeces;
    }

    /**
     * Définit la date de décès de la personne.
     *
     * @param dateDeces La date de décès de la personne.
     */
    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Obtient la nationalité de la personne.
     *
     * @return La nationalité de la personne.
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
 * Définit la nationalité de la personne.
 *
 * @param nationalite La nationalité de la personne.
 */
	public void setNationalite(String nationalite) {
    	this.nationalite = nationalite;
	}

	/**
	 * Obtient le genre de la personne.
	 *
	 * @return Le genre de la personne.
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * Définit le genre de la personne.
	 *
	 * @param genre Le genre de la personne.
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}

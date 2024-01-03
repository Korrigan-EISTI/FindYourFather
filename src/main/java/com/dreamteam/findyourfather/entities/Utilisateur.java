package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * La classe {@code Utilisateur} représente un utilisateur de l'application "Find Your Father".
 * Chaque utilisateur est associé à une personne et a des attributs tels que le numéro de téléphone,
 * l'adresse e-mail, le mot de passe et le niveau de visibilité.
 * 
 * <p>La classe utilise l'annotation {@code @Entity} pour indiquer qu'elle est une entité JPA,
 * et elle implémente {@code Serializable} pour permettre la sérialisation de l'objet.</p>
 *
 * <pre>{@code
 * public class Utilisateur implements Serializable {
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
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * L'énumération {@code Visiblity} définit les niveaux de visibilité possibles pour un utilisateur.
     * Les valeurs possibles sont PUBLIC, PRIVATE et PROTECTED.
     */
    public enum Visiblity {
        PUBLIC,
        PRIVATE,
        PROTECTED
    }

    @Id
    @GeneratedValue
    private Long id;
    private Long idPersonne;
    private Long phoneNumber;
    private String email;
    private String mdp;
    private Visiblity visibilityLevel;

    /**
     * Constructeur par défaut de l'utilisateur.
     */
    public Utilisateur() {
        super();
    }

    /**
     * Constructeur paramétré de l'utilisateur.
     *
     * @param idPersonne      L'ID de la personne associée à l'utilisateur.
     * @param phoneNumber     Le numéro de téléphone de l'utilisateur.
     * @param email           L'adresse e-mail de l'utilisateur.
     * @param mdp             Le mot de passe de l'utilisateur.
     * @param visibilityLevel Le niveau de visibilité de l'utilisateur (PUBLIC, PRIVATE, PROTECTED).
     */
    public Utilisateur(Long idPersonne, Long phoneNumber, String email, String mdp, Visiblity visibilityLevel) {
        super();
        this.idPersonne = idPersonne;
        this.setPhoneNumber(phoneNumber);
        this.email = email;
        this.mdp = mdp;
        this.visibilityLevel = visibilityLevel;
    }

    /**
     * Obtient l'ID de l'utilisateur.
     *
     * @return L'ID de l'utilisateur.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'ID de l'utilisateur.
     *
     * @param id L'ID de l'utilisateur.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient l'ID de la personne associée à l'utilisateur.
     *
     * @return L'ID de la personne associée à l'utilisateur.
     */
    public Long getIdPersonne() {
        return idPersonne;
    }

    /**
     * Définit l'ID de la personne associée à l'utilisateur.
     *
     * @param idPersonne L'ID de la personne associée à l'utilisateur.
     */
    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Obtient le numéro de téléphone de l'utilisateur.
     *
     * @return Le numéro de téléphone de l'utilisateur.
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Définit le numéro de téléphone de l'utilisateur.
     *
     * @param phoneNumber Le numéro de téléphone de l'utilisateur.
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Obtient l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param mdp Le mot de passe de l'utilisateur.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Obtient le niveau de visibilité de l'utilisateur.
     *
     * @return Le niveau de visibilité de l'utilisateur.
     */
    public Visiblity getVisibilityLevel() {
        return visibilityLevel;
    }

    /**
     * Définit le niveau de visibilité de l'utilisateur.
     *
     * @param visibilityLevel Le niveau de visibilité de l'utilisateur.
     */
    public void setVisibilityLevel(Visiblity visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }
}

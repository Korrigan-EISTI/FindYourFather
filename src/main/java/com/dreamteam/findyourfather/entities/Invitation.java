package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * La classe {@code Invitation} représente une invitation entre utilisateurs dans l'application
 * "Find Your Father". Les invitations sont utilisées pour établir des relations entre les personnes,
 * telles que père, mère, et enfant.
 * 
 * <p>Chaque invitation a un ID unique généré automatiquement, un ID d'utilisateur émetteur,
 * un ID de la personne source (root), un ID de la personne cible (target) et une relation spécifiée.</p>
 *
 * <p>La relation est définie par l'énumération {@code Relation}, qui peut être FATHER, MOTHER ou CHILD.</p>
 *
 * <pre>{@code
 * public class Invitation implements Serializable {
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
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * L'énumération {@code Relation} définit les types de relations possibles pour une invitation.
     * Les valeurs possibles sont FATHER, MOTHER, et CHILD.
     */
    public enum Relation {
        FATHER,
        MOTHER,
        CHILD
    }

    @Id
    @GeneratedValue
    private Long id;
    private Long idUser;
    private Long root;
    private Long target;
    private String relation;

    /**
     * Constructeur par défaut de l'invitation.
     */
    public Invitation() {
        super();
    }

    /**
     * Constructeur paramétré de l'invitation.
     *
     * @param idUser    L'ID de l'utilisateur émetteur de l'invitation.
     * @param root      L'ID de la personne source (root) de l'invitation.
     * @param target    L'ID de la personne cible (target) de l'invitation.
     * @param relation  La relation spécifiée pour l'invitation (FATHER, MOTHER, CHILD).
     */
    public Invitation(Long idUser, Long root, Long target, String relation) {
        super();
        this.idUser = idUser;
        this.root = root;
        this.target = target;
        this.relation = relation;
    }

    /**
     * Obtient l'ID de l'invitation.
     *
     * @return L'ID de l'invitation.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'ID de l'invitation.
     *
     * @param id L'ID de l'invitation.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient la relation de l'invitation.
     *
     * @return La relation de l'invitation.
     */
    public String getRelation() {
        return relation;
    }

    /**
     * Définit la relation de l'invitation.
     *
     * @param relation La relation de l'invitation.
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * Obtient l'ID de l'utilisateur émetteur de l'invitation.
     *
     * @return L'ID de l'utilisateur émetteur de l'invitation.
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Définit l'ID de l'utilisateur émetteur de l'invitation.
     *
     * @param idUser L'ID de l'utilisateur émetteur de l'invitation.
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * Obtient l'ID de la personne source (root) de l'invitation.
     *
     * @return L'ID de la personne source (root) de l'invitation.
     */
    public Long getRoot() {
        return root;
    }

    /**
     * Définit l'ID de la personne source (root) de l'invitation.
     *
     * @param from L'ID de la personne source (root) de l'invitation.
     */
    public void setRoot(Long from) {
        this.root = from;
    }

    /**
     * Obtient l'ID de la personne cible (target) de l'invitation.
     *
     * @return L'ID de la personne cible (target) de l'invitation.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * Définit l'ID de la personne cible (target) de l'invitation.
     *
     * @param target L'ID de la personne cible (target) de l'invitation.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
}

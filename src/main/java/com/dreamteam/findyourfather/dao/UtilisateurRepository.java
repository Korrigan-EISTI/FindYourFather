package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dreamteam.findyourfather.entities.Utilisateur;

/**
 * L'interface {@code UtilisateurRepository} est une extension de {@code JpaRepository} de Spring Data
 * destinée à la gestion des entités {@code Utilisateur}. Elle fournit des méthodes de base pour
 * l'accès aux données de la table des utilisateurs dans la base de données.
 * 
 * <p>Cette interface propose des méthodes pour récupérer des utilisateurs en fonction de leur
 * adresse e-mail, de l'ID de la personne associée ou de l'ID de l'utilisateur.</p>
 *
 * <pre>{@code
 * public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
 *     List<Utilisateur> findByEmail(String email);
 *     Utilisateur findUserByPersonneId(Long id);
 *     List<Utilisateur> findByIdPersonne(Long IdPersonne);
 * }
 * }</pre>
 *
 * <p>Assurez-vous d'avoir correctement configuré votre projet avec les dépendances Spring Data
 * nécessaires pour assurer le bon fonctionnement de l'interface.</p>
 * 
 * @author MARTIN MACE DE GASTINES
 * @author LOUIS-ALEXANDRE LAGUET
 * @author ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see JpaRepository
 * @see Utilisateur
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Récupère la liste des utilisateurs ayant une adresse e-mail spécifiée.
     *
     * @param email L'adresse e-mail pour laquelle récupérer les utilisateurs.
     * @return La liste des utilisateurs ayant l'adresse e-mail spécifiée.
     */
    List<Utilisateur> findByEmail(String email);

    /**
     * Récupère l'utilisateur associé à une personne spécifiée.
     *
     * @param id L'ID de la personne pour laquelle récupérer l'utilisateur.
     * @return L'utilisateur associé à la personne spécifiée.
     */
    @Query(value="SELECT * FROM utilisateur WHERE utilisateur.id_personne = ?1", nativeQuery = true)
    Utilisateur findUserByPersonneId(Long id);

    /**
     * Récupère la liste des utilisateurs associés à une personne spécifiée.
     *
     * @param IdPersonne L'ID de la personne pour laquelle récupérer les utilisateurs.
     * @return La liste des utilisateurs associés à la personne spécifiée.
     */
    List<Utilisateur> findByIdPersonne(Long IdPersonne);
}

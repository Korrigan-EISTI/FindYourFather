package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Utilisateur;

/**
 * L'interface {@code InvitationRepository} est une extension de {@code JpaRepository} de Spring Data
 * destinée à la gestion des entités {@code Invitation}. Elle fournit des méthodes de base pour
 * l'accès aux données de la table des invitations dans la base de données.
 * 
 * <p>Cette interface permet de définir des requêtes personnalisées pour récupérer des invitations
 * en fonction de certains critères, telles que l'ID de l'utilisateur ou la cible de l'invitation.</p>
 *
 * <p>Les méthodes déclarées dans cette interface seront automatiquement implémentées par Spring Data
 * lors de l'exécution de l'application.</p>
 *
 * <pre>{@code
 * public interface InvitationRepository extends JpaRepository<Invitation, Long> {
 *     List<Invitation> findByIdUser(Long IdUser);
 *     List<Invitation> findByTarget(Long target);
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
 * @see Invitation
 * @see Utilisateur
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    /**
     * Récupère la liste des invitations associées à un utilisateur spécifié.
     *
     * @param IdUser L'ID de l'utilisateur pour lequel récupérer les invitations.
     * @return La liste des invitations associées à l'utilisateur.
     */
    List<Invitation> findByIdUser(Long IdUser);

    /**
     * Récupère la liste des invitations ayant une cible spécifiée.
     *
     * @param target La cible de l'invitation pour laquelle récupérer les invitations.
     * @return La liste des invitations ayant la cible spécifiée.
     */
    List<Invitation> findByTarget(Long target);
}


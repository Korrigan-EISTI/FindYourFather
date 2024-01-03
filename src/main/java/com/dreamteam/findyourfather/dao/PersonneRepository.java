package com.dreamteam.findyourfather.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dreamteam.findyourfather.entities.Personne;

/**
 * L'interface {@code PersonneRepository} est une extension de {@code JpaRepository} de Spring Data
 * destinée à la gestion des entités {@code Personne}. Elle fournit des méthodes de base pour
 * l'accès aux données de la table des personnes dans la base de données.
 * 
 * <p>Cette interface propose des requêtes personnalisées à l'aide de l'annotation {@code @Query}
 * pour récupérer des personnes en fonction de critères spécifiques tels que le père, la mère,
 * le nom, le prénom ou le numéro de sécurité sociale.</p>
 *
 * <pre>{@code
 * public interface PersonneRepository extends JpaRepository<Personne, Long> {
 *     Set<Personne> findByFather(Long id);
 *     Set<Personne> findByMother(Long id);
 *     Personne findByName(String name, String firstName, String naissance);
 *     List<Personne> findByNumeroSecu(Long numeroSecu);
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
 * @see Personne
 */
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    /**
     * Récupère l'ensemble des personnes ayant un père spécifié.
     *
     * @param id L'ID du père pour lequel récupérer les personnes.
     * @return L'ensemble des personnes ayant le père spécifié.
     */
    @Query(value="SELECT * FROM personne WHERE personne.pere = ?1", nativeQuery = true)
    Set<Personne> findByFather(Long id);

    /**
     * Récupère l'ensemble des personnes ayant une mère spécifiée.
     *
     * @param id L'ID de la mère pour laquelle récupérer les personnes.
     * @return L'ensemble des personnes ayant la mère spécifiée.
     */
    @Query(value="SELECT * FROM personne WHERE personne.mere = ?1", nativeQuery = true)
    Set<Personne> findByMother(Long id);

    /**
     * Récupère une personne en fonction du nom, du prénom et de la date de naissance spécifiés.
     *
     * @param name      Le nom de la personne.
     * @param firstName Le prénom de la personne.
     * @param naissance La date de naissance de la personne.
     * @return La personne correspondant aux critères spécifiés.
     */
    @Query(value="SELECT * FROM personne WHERE personne.nom = ?1 AND personne.prenom = ?2 AND personne.naissance = ?3", nativeQuery = true)
    Personne findByName(String name, String firstName, String naissance);

    /**
     * Récupère la liste des personnes ayant un numéro de sécurité sociale spécifié.
     *
     * @param numeroSecu Le numéro de sécurité sociale pour lequel récupérer les personnes.
     * @return La liste des personnes ayant le numéro de sécurité sociale spécifié.
     */
    List<Personne> findByNumeroSecu(Long numeroSecu);
}

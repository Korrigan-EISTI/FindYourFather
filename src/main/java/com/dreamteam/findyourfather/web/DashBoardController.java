package com.dreamteam.findyourfather.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Personne;

import jakarta.servlet.http.HttpSession;

/**
 * Le contrôleur {@code DashBoardController} gère les requêtes liées au tableau de bord
 * de l'application "Find Your Father".
 * 
 * <p>Ce contrôleur utilise l'annotation {@code @RestController} pour indiquer qu'il est un composant
 * de contrôleur Spring qui traite les requêtes REST. Il expose des méthodes pour obtenir des informations
 * sur une personne par ID et récupérer les invitations associées à un utilisateur connecté.</p>
 *
 * <pre>{@code
 * @RestController
 * public class DashBoardController {
 *     // ...
 * }
 * }</pre>
 * 
 * <p>Assurez-vous que les annotations et les dépendances nécessaires sont correctement
 * configurées pour une utilisation avec Spring Framework.</p>
 * 
 * @author MARTIN MACE DE GASTINES
 * @author LOUIS-ALEXANDRE LAGUET
 * @author ALEXIS TOURRENC--LECERF
 * @version 1.0
 * @since 2024-01-03
 * @see RestController
 * @see GetMapping
 * @see PostMapping
 * @see PathVariable
 * @see InvitationRepository
 * @see UtilisateurRepository
 * @see PersonneRepository
 * @see Invitation
 * @see Personne
 * @see HttpSession
 */
@RestController
public class DashBoardController {

    private final InvitationRepository invitationRepository;
    private final UtilisateurRepository utilisateurRepository;
	private final PersonneRepository personneRepository;

    /**
     * Constructeur du contrôleur du tableau de bord.
     *
     * @param invitationRepository  Le référentiel des invitations.
     * @param utilisateurRepository Le référentiel des utilisateurs.
     * @param personneRepository    Le référentiel des personnes.
     */
    public DashBoardController(InvitationRepository invitationRepository, UtilisateurRepository utilisateurRepository, PersonneRepository personneRepository) {
        this.invitationRepository = invitationRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.personneRepository = personneRepository;
    }
    
    /**
     * Obtient les informations d'une personne en fonction de son ID.
     *
     * @param id L'ID de la personne.
     * @return   Les informations de la personne ou {@code Optional.empty()} si non trouvé.
     */
    @GetMapping("/getPersonneById/{id}")
    public Optional<Personne> getPersonneById(@PathVariable Long id) {
        return personneRepository.findById(id);
    }
    
    /**
     * Récupère les invitations associées à l'utilisateur connecté.
     *
     * @param session La session HTTP.
     * @return        La liste des invitations associées à l'utilisateur connecté.
     */
	@PostMapping(path = "/getInvitations", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Invitation> getInvitations(HttpSession session){
		return invitationRepository.findByTarget(utilisateurRepository.getReferenceById((Long)session.getAttribute("user")).getIdPersonne());
	}
}

package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.entities.Invitation;

/**
 * Les tests pour les requêtes sur les invitations sont gérés par la classe {@code QueryInvitationTest}.
 *
 * <p>Cette classe utilise l'annotation {@code @SpringBootTest} pour indiquer qu'il s'agit d'un test Spring Boot.
 * Elle est équipée d'un champ {@code InvitationRepository} autowired pour interagir avec les invitations en base de données.</p>
 *
 * <pre>{@code
 * @SpringBootTest
 * class QueryInvitationTest {
 *     @Autowired
 *     private InvitationRepository invRep;
 *
 *     @Test
 *     void testFindByIdUser() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindByTarget() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testSaveInvitation() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testRemoveInvitation() {
 *         // ...
 *     }
 * }
 * }</pre>
 *
 * <p>Assurez-vous que les annotations et les dépendances nécessaires sont correctement configurées pour une utilisation avec Spring Boot.</p>
 * 
 * @version 1.0
 * @since 2024-01-03
 * @see Test
 * @see SpringBootTest
 * @see Autowired
 */
@SpringBootTest
public class QueryInvitationTest {
	
	/**
     * Le repository pour les invitations.
     */
	@Autowired
	private InvitationRepository invRep;
	
	/**
     * Teste la recherche des invitations par ID utilisateur.
     */
	@Test
	public void testFindByIdUser() {
		for (Invitation inv : invRep.findByIdUser((long) 1)) {
			assertEquals(1, inv.getId());
		}
	}
	
	/**
     * Teste la recherche des invitations par cible.
     */
	@Test
	public void testFindByTarget() {
		for (Invitation inv : invRep.findByTarget((long) 2)) {
			assertEquals(1, inv.getId());
		}
	}
	
	/**
     * Teste l'ajout d'une invitation.
     */
	@Test
	public void testSaveInvitation() {
		Invitation inv = new Invitation((long) 2, null, (long) 3, null);
		assertDoesNotThrow(() -> invRep.save(inv));
		for (Invitation i : invRep.findByIdUser((long) 2)) {
			assertEquals(inv.getId(), i.getId());
		}
	}
	
	/**
     * Teste la suppression d'une invitation.
     */
	@Test
	public void testRemoveInvitation() {
		for (Invitation i : invRep.findByIdUser((long) 2)) {
			assertDoesNotThrow(() -> invRep.delete(i));
		}
		assertEquals(true, invRep.findByIdUser((long) 2).isEmpty());
	}
}

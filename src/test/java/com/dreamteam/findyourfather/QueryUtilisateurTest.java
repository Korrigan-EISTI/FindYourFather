package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Utilisateur;

/**
 * Les tests pour les requêtes sur les utilisateurs sont gérés par la classe {@code QueryUtilisateurTest}.
 *
 * <p>Cette classe utilise l'annotation {@code @SpringBootTest} pour indiquer qu'il s'agit d'un test Spring Boot.
 * Elle est équipée d'un champ {@code UtilisateurRepository} autowired pour interagir avec les utilisateurs en base de données.</p>
 *
 * <pre>{@code
 * @SpringBootTest
 * class QueryUtilisateurTest {
 *     @Autowired
 *     private UtilisateurRepository userRep;
 *
 *     @Test
 *     void testFindByEmail() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindUserById() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindUserByPersonneId() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testSaveUser() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testRemoveUser() {
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
public class QueryUtilisateurTest {

	/**
     * Le repository pour les utilisateurs.
     */
	@Autowired
	private UtilisateurRepository userRep;
	
	/**
     * Teste la recherche des utilisateurs par email.
     */
	@Test
	public void testFindByEmail() {
		for (Utilisateur user : userRep.findByEmail("test@test.com")){
			assertEquals(1, user.getId());
		}
	}
	
	/**
     * Teste la recherche des utilisateurs par ID de l'utilisateur.
     */
	@Test
	public void testFindUserById() {
		for (Utilisateur user : userRep.findByIdPersonne((long) 1)){
			assertEquals(1, user.getId());
		}
	}
	
	/**
     * Teste la recherche des utilisateurs par ID de personne.
     */
	@Test
	public void testFindUserByPersonneId(){
		assertEquals(1, userRep.findUserByPersonneId((long) 1).getId());
	}
	
	/**
     * Teste l'ajout d'un utilisateur.
     */
	@Test
	public void testSaveUser() {
		Utilisateur user = new Utilisateur((long)3, null, "unitTest@unitTest.com", "unitTest", null);
		assertDoesNotThrow(() -> userRep.save(user));
		assertEquals(user.getId(), userRep.findUserByPersonneId((long) 3).getId());
	}
	
	/**
     * Teste la suppression d'un utilisateur.
     */
	@Test
	public void testRemoveUser() {
		Utilisateur user = userRep.findUserByPersonneId((long) 3);
		assertDoesNotThrow(() -> userRep.delete(user));
		assertEquals(null, userRep.findUserByPersonneId((long) 3));
	}
}

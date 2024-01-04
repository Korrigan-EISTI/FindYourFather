package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.entities.Personne;

/**
 * Les tests pour les requêtes sur les personnes sont gérés par la classe {@code QueryPersonneTests}.
 *
 * <p>Cette classe utilise l'annotation {@code @SpringBootTest} pour indiquer qu'il s'agit d'un test Spring Boot.
 * Elle est équipée d'un champ {@code PersonneRepository} autowired pour interagir avec les personnes en base de données.</p>
 *
 * <pre>{@code
 * @SpringBootTest
 * class QueryPersonneTests {
 *     @Autowired
 *     private PersonneRepository persRepository;
 *
 *     @Test
 *     void testFindByFather() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindByMother() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindById() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindByNumSecu() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testFindByName() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testAddPersonne() {
 *         // ...
 *     }
 *
 *     @Test
 *     void testRemovePersonne() {
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
public class QueryPersonneTests {
	/**
     * Le repository pour les personnes.
     */
	@Autowired 
	private PersonneRepository persRepository;
	
	/**
     * Teste la recherche des personnes par père.
     */
	@Test
	public void testFindByFather() {
		for (Personne pers : persRepository.findByFather((long) 2)){
			assertEquals(1, pers.getId());
		}
	}
	
	/**
     * Teste la recherche des personnes par mère.
     */
	@Test
	public void testFindByMother() {
		for (Personne pers : persRepository.findByMother((long) 3)){
			assertEquals(1, pers.getId());
		}
	}
	
	/**
     * Teste la recherche des personnes par ID.
     */
	@Test
	public void testFindById(){
		assertEquals(1, persRepository.findById((long) 1).get().getId());
	}
	
	/**
     * Teste la recherche des personnes par numéro de sécurité sociale.
     */
	@Test
	public void testFindByNumSecu() {
		for (Personne pers : persRepository.findByNumeroSecu((long) 123456789)){
			assertEquals(1, pers.getId());
		}
	}
	
	/**
     * Teste la recherche des personnes par nom.
     */
	@Test
	public void testFindByName() {
		assertEquals(1, persRepository.findByName("Doe", "John", "01/01/1990").getId());
	}
	
	/**
     * Teste l'ajout d'une personne.
     */
	@Test
	public void testAddPersonne() {
		Personne pers = new Personne((long)01254, "Test", "Test");
		pers.setNaissance("01/01/1900");
		assertDoesNotThrow(() -> persRepository.save(pers));
		assertEquals(pers.getId(), persRepository.findByName("Test", "Test", "01/01/1900").getId());
	}
	
	/**
     * Teste la suppression d'une personne.
     */
	@Test
	public void testRemovePersonne() {
		Personne pers = persRepository.findByName("Test", "Test", "01/01/1900");
		assertDoesNotThrow(() -> persRepository.delete(pers));
		assertEquals(null, persRepository.findByName("Test", "Test", "01/01/1900"));
	}
}

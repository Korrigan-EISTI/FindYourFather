package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.entities.Personne;

@SpringBootTest
public class QueryPersonneTests {
	@Autowired 
	private PersonneRepository persRepository;
	
	@Test
	public void testFindByFather() {
		for (Personne pers : persRepository.findByFather((long) 2)){
			assertEquals(1, pers.getId());
		}
	}
	
	@Test
	public void testFindByMother() {
		for (Personne pers : persRepository.findByMother((long) 3)){
			assertEquals(1, pers.getId());
		}
	}
	
	@Test
	public void testFindById(){
		assertEquals(1, persRepository.findById((long) 1).get().getId());
	}
	
	@Test
	public void testFindByNumSecu() {
		for (Personne pers : persRepository.findByNumeroSecu((long) 123456789)){
			assertEquals(1, pers.getId());
		}
	}
	
	@Test
	public void testFindByName() {
		assertEquals(1, persRepository.findByName("Doe", "John", "01/01/1990").getId());
	}
	
	@Test
	public void testAddPersonne() {
		Personne pers = new Personne((long)01254, "Test", "Test");
		pers.setNaissance("01/01/1900");
		assertDoesNotThrow(() -> persRepository.save(pers));
		assertEquals(pers.getId(), persRepository.findByName("Test", "Test", "01/01/1900").getId());
	}
	
	@Test
	public void testRemovePersonne() {
		Personne pers = persRepository.findByName("Test", "Test", "01/01/1900");
		assertDoesNotThrow(() -> persRepository.delete(pers));
		assertEquals(null, persRepository.findByName("Test", "Test", "01/01/1900"));
	}
}

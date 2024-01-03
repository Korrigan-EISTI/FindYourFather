package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Utilisateur;

@SpringBootTest
public class QueryUtilisateurTest {

	@Autowired
	private UtilisateurRepository userRep;
	
	@Test
	public void testFindByEmail() {
		for (Utilisateur user : userRep.findByEmail("test@test.com")){
			assertEquals(1, user.getId());
		}
	}
	
	@Test
	public void testFindUserById() {
		for (Utilisateur user : userRep.findByIdPersonne((long) 1)){
			assertEquals(1, user.getId());
		}
	}
	
	@Test
	public void testFindUserByPersonneId(){
		assertEquals(1, userRep.findUserByPersonneId((long) 1).getId());
	}
	
	@Test
	public void testSaveUser() {
		Utilisateur user = new Utilisateur((long)3, null, "unitTest@unitTest.com", "unitTest", null);
		assertDoesNotThrow(() -> userRep.save(user));
		assertEquals(user.getId(), userRep.findUserByPersonneId((long) 3).getId());
	}
	
	@Test
	public void testRemoveUser() {
		Utilisateur user = userRep.findUserByPersonneId((long) 3);
		assertDoesNotThrow(() -> userRep.delete(user));
		assertEquals(null, userRep.findUserByPersonneId((long) 3));
	}
}

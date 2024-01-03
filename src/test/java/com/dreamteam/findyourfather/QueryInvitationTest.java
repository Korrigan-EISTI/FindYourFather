package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.entities.Invitation;

@SpringBootTest
public class QueryInvitationTest {
	
	@Autowired
	private InvitationRepository invRep;
	
	@Test
	public void testFindByIdUser() {
		for (Invitation inv : invRep.findByIdUser((long) 1)) {
			assertEquals(1, inv.getId());
		}
	}
	
	@Test
	public void testFindByTarget() {
		for (Invitation inv : invRep.findByTarget((long) 2)) {
			assertEquals(1, inv.getId());
		}
	}
	
	@Test
	public void testSaveInvitation() {
		Invitation inv = new Invitation((long) 2, null, (long) 3, null);
		assertDoesNotThrow(() -> invRep.save(inv));
		for (Invitation i : invRep.findByIdUser((long) 2)) {
			assertEquals(inv.getId(), i.getId());
		}
	}
	
	@Test
	public void testRemoveInvitation() {
		for (Invitation i : invRep.findByIdUser((long) 2)) {
			assertDoesNotThrow(() -> invRep.delete(i));
		}
		assertEquals(true, invRep.findByIdUser((long) 2).isEmpty());
	}
}

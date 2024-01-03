package com.dreamteam.findyourfather;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
}

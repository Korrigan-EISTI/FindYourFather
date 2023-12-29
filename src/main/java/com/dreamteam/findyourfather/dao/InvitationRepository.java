package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Invitation;
import com.dreamteam.findyourfather.entities.Utilisateur;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{

	List<Invitation> findByIdUser(Long IdUser);
	List<Invitation> findByTarget(Long target);
}

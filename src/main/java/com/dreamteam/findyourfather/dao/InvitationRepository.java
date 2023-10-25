package com.dreamteam.findyourfather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{

}

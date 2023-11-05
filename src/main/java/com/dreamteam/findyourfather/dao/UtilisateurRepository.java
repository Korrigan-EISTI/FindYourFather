package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	List<Utilisateur> findByEmail(String email);

}

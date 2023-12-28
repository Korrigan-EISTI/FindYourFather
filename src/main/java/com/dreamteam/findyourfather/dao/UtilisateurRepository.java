package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dreamteam.findyourfather.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	List<Utilisateur> findByEmail(String email);
	
	@Query(value="SELECT * FROM utilisateur WHERE utilisateur.idPersonne = ?1", nativeQuery = true)
	Utilisateur findUserByPersonneId(Long id);

	List<Utilisateur> findByIdPersonne(Long IdPersonne);
}
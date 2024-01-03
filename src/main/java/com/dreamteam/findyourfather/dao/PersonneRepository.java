package com.dreamteam.findyourfather.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dreamteam.findyourfather.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{
	
	@Query(value="SELECT * FROM personne WHERE personne.pere = ?1", nativeQuery = true)
	Set<Personne> findByFather(Long id);
	
	@Query(value="SELECT * FROM personne WHERE personne.mere = ?1", nativeQuery = true)
	Set<Personne> findByMother(Long id);
	
	@Query(value="SELECT * FROM personne WHERE personne.nom = ?1 AND personne.prenom = ?2 AND personne.naissance = ?3", nativeQuery = true)
	Personne findByName(String name, String firstName, String naissance);
	
	List<Personne> findByNumeroSecu(Long numeroSecu);
}

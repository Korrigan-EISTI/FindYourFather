package com.dreamteam.findyourfather.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dreamteam.findyourfather.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{
	@Query(value = "SELECT * FROM public.personne AS p WHERE p.id = ?1", nativeQuery = true)
	Personne findOnePersonne(String id);
}

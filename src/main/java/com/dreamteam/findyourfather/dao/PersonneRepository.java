package com.dreamteam.findyourfather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}

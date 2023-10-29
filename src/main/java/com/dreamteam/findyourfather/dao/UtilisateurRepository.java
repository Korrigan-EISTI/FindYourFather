package com.dreamteam.findyourfather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

}

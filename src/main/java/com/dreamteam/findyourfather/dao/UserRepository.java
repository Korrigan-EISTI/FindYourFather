package com.dreamteam.findyourfather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.UserOfTree;

public interface UserRepository extends JpaRepository<UserOfTree, Long>{

}

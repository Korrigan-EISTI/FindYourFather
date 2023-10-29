package com.dreamteam.findyourfather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamteam.findyourfather.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

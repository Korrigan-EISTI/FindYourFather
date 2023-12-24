package com.dreamteam.findyourfather.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserRequest {
	
	@Id @GeneratedValue
	Long id;
	Long userId1;
	Long userId2;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId1() {
		return userId1;
	}
	public void setUserId1(Long userId1) {
		this.userId1 = userId1;
	}
	public Long getUserId2() {
		return userId2;
	}
	public void setUserId2(Long userId2) {
		this.userId2 = userId2;
	}
	
	public UserRequest(Long idUser1, Long idUser2) {
		this.userId1 = idUser1;
		this.userId2 = idUser2;
	}
	
}

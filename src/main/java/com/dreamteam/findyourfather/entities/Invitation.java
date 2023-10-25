package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Invitation implements Serializable{
	
	public enum Status{
		ACCEPTED,
		WAITING,
		REFUSED
	}
	
	@Id@GeneratedValue
	private Long id;
	private Long idUser1;
	private Long idUser2;
	private Status status;
	
	public Invitation(Long id, Long idUser1, Long idUser2, Status status) {
		super();
		this.id = id;
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser1() {
		return idUser1;
	}

	public void setIdUser1(Long idUser1) {
		this.idUser1 = idUser1;
	}

	public Long getIdUser2() {
		return idUser2;
	}

	public void setIdUser2(Long idUser2) {
		this.idUser2 = idUser2;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}

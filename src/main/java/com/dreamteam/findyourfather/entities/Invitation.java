package com.dreamteam.findyourfather.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Invitation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public enum Status{
		PENDING,
		ACCEPTED,
		REFUSED
	}
	
	public enum Relation{
		FATHER,
		MOTHER,
		CHILD
	}
	
	@Id @GeneratedValue
	private Long id;
	private Long idUser;
	private Long root;
	private Long target;
	private String relation;
	private Status status;
	
	public Invitation(Long idUser, Long root, Long target,String relation, Status status) {
		super();
		this.idUser= idUser;
		this.root= root;
		this.target= target;
		this.status = status;
		this.relation = relation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getRoot() {
		return root;
	}

	public void setRoot(Long from) {
		this.root = from;
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
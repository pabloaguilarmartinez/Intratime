package com.mindden.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mindden.enums.RoleType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Integer id;
	
	private String email;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private Set<Checking> checkings = new HashSet<Checking>();
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private Set<Request> requests = new HashSet<Request>();

}
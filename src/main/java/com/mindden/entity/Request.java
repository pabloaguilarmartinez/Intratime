package com.mindden.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mindden.enums.CheckingType;
import com.mindden.enums.RequestStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime date;
	
	@Enumerated(EnumType.STRING)
	private CheckingType checkingType;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;	

}

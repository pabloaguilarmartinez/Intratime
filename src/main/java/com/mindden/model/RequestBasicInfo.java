package com.mindden.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mindden.enums.CheckingType;
import com.mindden.enums.RequestStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBasicInfo {
	
	private Integer id;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private CheckingType checkingType;
	@NotBlank
	private RequestStatus status;
	private String description;

}

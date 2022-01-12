package com.mindden.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.mindden.enums.CheckingType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingBasicInfo {

	private Integer id;
	@NotNull
	private LocalDateTime date;
	@NotNull
	private CheckingType type;
	
}

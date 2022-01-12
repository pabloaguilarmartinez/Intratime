package com.mindden.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mindden.enums.RoleType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
	
	private Integer id;
	@NotBlank
	private String email;
	private String password;
	@NotNull
	private RoleType role;

}

package com.mindden.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingInfo extends CheckingBasicInfo {
	
	@NotNull
	private UserInfo user;	
	
}

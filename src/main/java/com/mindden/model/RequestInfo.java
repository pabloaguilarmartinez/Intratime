package com.mindden.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInfo extends RequestBasicInfo {
	
	@NotNull
	private UserInfo user;	

}

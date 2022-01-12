package com.mindden.controller;

import static com.mindden.constants.Constants.USERS_URI;
import static com.mindden.constants.Constants.CHECKINGS_URI;
import static com.mindden.constants.Constants.REQUESTS_URI;
import static com.mindden.constants.Constants.ID_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = USERS_URI, produces = APPLICATION_JSON_VALUE)
public class UsersController {
	
	private final UsersService usersService;
	
	@GetMapping
	public List<UserInfo> findUsers() {
		return usersService.findAll();
	}
	
	@GetMapping(ID_URI + CHECKINGS_URI)
	public Collection<CheckingBasicInfo> findCheckingsUserById(@PathVariable final Integer id) {
		return usersService.findCheckingsUserById(id);
	}
	
	@GetMapping(ID_URI + REQUESTS_URI)
	public Collection<RequestBasicInfo> findRequestsUserById(@PathVariable final Integer id) {
		return usersService.findRequestsUserById(id);
	}
}

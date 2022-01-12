package com.mindden.controller;

import static com.mindden.constants.Constants.CHECKINGS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindden.model.CheckingInfo;
import com.mindden.service.CheckingsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = CHECKINGS_URI, produces = APPLICATION_JSON_VALUE)
public class CheckingsController {
	
	private final CheckingsService checkingsService;
	
	@GetMapping
	public Collection<CheckingInfo> findAll(){
		return checkingsService.findAll();
	}
	
	@PostMapping
	public void create(@RequestBody CheckingInfo dto) {
		checkingsService.create(dto);
	}

}

package com.mindden.controller;

import static com.mindden.constants.Constants.REQUESTS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindden.model.RequestInfo;
import com.mindden.service.RequestsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = REQUESTS_URI, produces = APPLICATION_JSON_VALUE)
public class RequestsController {
	
	private final RequestsService requestsService;
	
	@GetMapping
	public Collection<RequestInfo> findAll(){
		return requestsService.findAll();
	}
	
	@PostMapping
	public void create(@RequestBody RequestInfo dto) {
		requestsService.create(dto);
	}
	
}
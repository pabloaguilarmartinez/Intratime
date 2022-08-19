package com.mindden.controller;

import static com.mindden.constants.Constants.REQUESTS_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Collection<RequestInfo>> findAll() {
		return ResponseEntity.ok(requestsService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<RequestInfo> create(@RequestBody RequestInfo dto) {
		URI location = URI.create(REQUESTS_URI);
		return ResponseEntity.created(location).body(requestsService.create(dto));
	}
	
}
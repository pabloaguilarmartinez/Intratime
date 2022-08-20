package com.mindden.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mindden.mapper.RequestsMapper;
import com.mindden.model.RequestInfo;
import com.mindden.repository.RequestsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestsService {
	
	private final RequestsRepository requestsRepository;
	private final RequestsMapper requestsMapper;
	
	@Transactional
	public RequestInfo create(RequestInfo dto) {
		return requestsMapper.toDto(requestsRepository.save(requestsMapper.toEntity(dto)));
	}
	
	public Collection<RequestInfo> findAll() {
		return requestsMapper.toDtos(requestsRepository.findAll());
	}

}

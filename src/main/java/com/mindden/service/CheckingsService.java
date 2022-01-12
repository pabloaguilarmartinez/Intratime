package com.mindden.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mindden.mapper.CheckingsMapper;
import com.mindden.model.CheckingInfo;
import com.mindden.repository.CheckingsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckingsService {
	
	private final CheckingsRepository checkingsRepository;
	private final CheckingsMapper checkingsMapper;
	
	@Transactional
	public void create(CheckingInfo dto) {	
		checkingsRepository.save(checkingsMapper.toEntity(dto));
	}
	
	public Collection<CheckingInfo> findAll() {
        return checkingsMapper.toDtos(checkingsRepository.findAll());
    }
	
}

package com.mindden.service;

import com.mindden.mapper.CheckingsMapper;
import com.mindden.model.CheckingInfo;
import com.mindden.repository.CheckingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CheckingsService {

    private final CheckingsRepository checkingsRepository;
    private final CheckingsMapper checkingsMapper;

    @Transactional
    public CheckingInfo create(CheckingInfo dto) {
        return checkingsMapper.toDto(checkingsRepository.save(checkingsMapper.toEntity(dto)));
    }

    public Collection<CheckingInfo> findAll() {
        return checkingsMapper.toDtos(checkingsRepository.findAll());
    }

}

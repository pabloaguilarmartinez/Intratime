package com.mindden.service;

import com.mindden.mapper.CheckingsMapper;
import com.mindden.model.CheckingInfo;
import com.mindden.repository.CheckingsRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckingsServiceTest {
    private final EasyRandom generator = new EasyRandom();

    @Mock
    private CheckingsMapper checkingsMapper = Mappers.getMapper(CheckingsMapper.class);

    @Mock
    private CheckingsRepository checkingsRepository;

    @InjectMocks
    private CheckingsService checkingsService;

    @Test
    void whenFindAll_thenReturnEmptyCollection() {
        Collection<CheckingInfo> emptyCollection = new ArrayList<>();
        when(checkingsMapper.toDtos(checkingsRepository.findAll())).thenReturn(emptyCollection);

        Collection<CheckingInfo> result = checkingsService.findAll();

        assertEquals(emptyCollection.size(), result.size());
        assertEquals(emptyCollection, result);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenFindAll_thenReturnCollectionOfCheckingInfo() {
        CheckingInfo checkingInfo = generator.nextObject(CheckingInfo.class);
        Collection<CheckingInfo> checkingInfoCollection = List.of(checkingInfo);
        when(checkingsMapper.toDtos(checkingsRepository.findAll())).thenReturn(checkingInfoCollection);

        Collection<CheckingInfo> result = checkingsService.findAll();

        assertEquals(checkingInfoCollection.size(), result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void whenCreateChecking_thenReturnCreatedChecking() {
        CheckingInfo createdChecking = generator.nextObject(CheckingInfo.class);
        when(checkingsMapper.toDto(checkingsRepository.save(checkingsMapper.toEntity(any())))).thenReturn(createdChecking);

        CheckingInfo result = checkingsService.create(new CheckingInfo());

        assertEquals(createdChecking.getUser(), result.getUser());
        assertEquals(createdChecking.getDate(), result.getDate());
        assertEquals(createdChecking.getId(), result.getId());
        assertEquals(createdChecking.getType(), result.getType());
    }
}

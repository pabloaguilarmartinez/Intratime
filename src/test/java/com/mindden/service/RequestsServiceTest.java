package com.mindden.service;

import com.mindden.mapper.RequestsMapper;
import com.mindden.model.RequestInfo;
import com.mindden.repository.RequestsRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestsServiceTest {
    private final EasyRandom generator = new EasyRandom();

    @Mock
    private RequestsMapper requestsMapper = Mappers.getMapper(RequestsMapper.class);

    @Mock
    private RequestsRepository requestsRepository;

    @InjectMocks
    private RequestsService requestsService;

    @Test
    void whenFindAll_thenReturnEmptyCollection() {
        Collection<RequestInfo> emptyCollection = generator.objects(RequestInfo.class, 0).collect(Collectors.toList());
        when(requestsMapper.toDtos(requestsRepository.findAll())).thenReturn(emptyCollection);

        Collection<RequestInfo> result = requestsService.findAll();

        assertEquals(emptyCollection.size(), result.size());
        assertEquals(emptyCollection, result);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenFindAll_thenReturnCollectionOfRequestInfo() {
        RequestInfo requestInfo = generator.nextObject(RequestInfo.class);
        Collection<RequestInfo> requestInfoCollection = List.of(requestInfo);
        when(requestsMapper.toDtos(requestsRepository.findAll())).thenReturn(requestInfoCollection);

        Collection<RequestInfo> result = requestsService.findAll();

        assertEquals(requestInfoCollection.size(), result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void whenCreateRequest_thenReturnCreatedRequest() {
            RequestInfo createdRequest = generator.nextObject(RequestInfo.class);
            when(requestsMapper.toDto(requestsRepository.save(requestsMapper.toEntity(any())))).thenReturn(createdRequest);

            RequestInfo result = requestsService.create(new RequestInfo());

            assertEquals(createdRequest.getUser(), result.getUser());
            assertEquals(createdRequest.getCheckingType(), result.getCheckingType());
            assertEquals(createdRequest.getStatus(), result.getStatus());
            assertEquals(createdRequest.getDescription(), result.getDescription());
            assertEquals(createdRequest.getDate(), result.getDate());
            assertEquals(createdRequest.getId(), result.getId());
    }
}

package com.mindden.mapper;

import com.mindden.entity.Request;
import com.mindden.model.RequestInfo;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestsMapperTest {
    private final EasyRandom generator = new EasyRandom();
    private final RequestsMapper requestsMapper = Mappers.getMapper(RequestsMapper.class);

    @Test
    void whenMapEntityRequestToDto_shouldReturnDto() {
        Request requestEntity = generator.nextObject(Request.class);

        RequestInfo requestInfo = requestsMapper.toDto(requestEntity);

        assertEquals(requestEntity.getCheckingType(), requestInfo.getCheckingType());
        assertEquals(requestEntity.getId(), requestInfo.getId());
        assertEquals(requestEntity.getDate(), requestInfo.getDate());
        assertEquals(requestEntity.getDescription(), requestInfo.getDescription());
        assertEquals(requestEntity.getUser().getId(), requestInfo.getUser().getId());
        assertEquals(requestEntity.getStatus(), requestInfo.getStatus());
    }

    @Test
    void whenMapDtoRequestToEntity_shouldReturnEntity() {
        RequestInfo requestInfo = generator.nextObject(RequestInfo.class);

        Request requestEntity = requestsMapper.toEntity(requestInfo);

        assertEquals(requestInfo.getId(), requestEntity.getId());
        assertEquals(requestInfo.getUser().getEmail(), requestEntity.getUser().getEmail());
        assertEquals(requestInfo.getDate(), requestEntity.getDate());
        assertEquals(requestInfo.getCheckingType(), requestEntity.getCheckingType());
        assertEquals(requestInfo.getDescription(), requestEntity.getDescription());
        assertEquals(requestInfo.getStatus(), requestEntity.getStatus());
    }

}

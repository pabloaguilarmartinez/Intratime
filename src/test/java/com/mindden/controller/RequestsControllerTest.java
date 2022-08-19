package com.mindden.controller;

import com.mindden.model.RequestInfo;
import com.mindden.service.RequestsService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RequestsControllerTest {

    private final EasyRandom generator = new EasyRandom();

    @Mock
    private RequestsService requestsService;

    @InjectMocks
    private RequestsController requestsController;

    @Test
    void whenFindAll_thenReturnAll() {
        Collection<RequestInfo> existingRequests = generator.objects(RequestInfo.class, 4)
                .collect(Collectors.toList());
        when(requestsService.findAll()).thenReturn(existingRequests);

        Collection<RequestInfo> response = requestsController.findAll().getBody();

        assertEquals(existingRequests, response);
        assert response != null;
        assertEquals(4, response.size());
    }

    @Test
    void whenCreateRequest_thenReturnCreatedRequest() {
        RequestInfo request = generator.nextObject(RequestInfo.class);

        ResponseEntity<RequestInfo> response = requestsController.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

package com.mindden.controller;

import com.mindden.model.CheckingInfo;
import com.mindden.service.CheckingsService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CheckingsControllerTest {

    private final EasyRandom generator = new EasyRandom();

    @Mock
    private CheckingsService checkingsService;
    @InjectMocks
    private CheckingsController checkingsController;


    @Test
    void whenFindAll_thenReturnAll() {
        Collection<CheckingInfo> existingCheckings = generator.objects(CheckingInfo.class, 5)
                .collect(Collectors.toList());
        when(checkingsService.findAll()).thenReturn(existingCheckings);

        ResponseEntity<Collection<CheckingInfo>> response = checkingsController.findAll();

        assertEquals(existingCheckings, response.getBody());
        assertEquals(5, Objects.requireNonNull(response.getBody()).size());

    }

    @Test
    void whenCreateChecking_thenReturnCreatedChecking() {
        CheckingInfo request = generator.nextObject(CheckingInfo.class);

        ResponseEntity<CheckingInfo> response = checkingsController.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

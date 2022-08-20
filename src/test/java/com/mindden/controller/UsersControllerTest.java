package com.mindden.controller;

import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.service.UsersService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    private final EasyRandom generator = new EasyRandom();

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @Test
    void whenFindAll_thenReturnAll() {
        Collection<UserInfo> existingUsers = generator.objects(UserInfo.class, 5)
                .collect(Collectors.toList());
        when(usersService.findAll()).thenReturn(existingUsers);

        ResponseEntity<Collection<UserInfo>> response = usersController.findUsers();

        assertEquals(existingUsers, response.getBody());
        assert response != null;
        assertEquals(existingUsers.size(), response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenFindCheckingsByUserId_thenReturnCheckings() {
        Collection<CheckingBasicInfo> userCheckings = generator.objects(CheckingBasicInfo.class, 6)
                .collect(Collectors.toList());
        when(usersService.findCheckingsUserById(any())).thenReturn(userCheckings);

        ResponseEntity<Collection<CheckingBasicInfo>> response = usersController.findCheckingsUserById(1);

        assertEquals(userCheckings, response.getBody());
        assert response != null;
        assertEquals(userCheckings.size(), response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenFindRequestsByUserId_thenReturnRequests() {
        Collection<RequestBasicInfo> userRequests = generator.objects(RequestBasicInfo.class, 7)
                .collect(Collectors.toList());
        when(usersService.findRequestsUserById(any())).thenReturn(userRequests);

        ResponseEntity<Collection<RequestBasicInfo>> response = usersController.findRequestsUserById(1);

        assertEquals(userRequests, response.getBody());
        assert response != null;
        assertEquals(userRequests.size(), response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}

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

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

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

        Collection<UserInfo> response = usersController.findUsers().getBody();

        assertEquals(existingUsers, response);
        assertEquals(5, response.size());
    }

    @Test
    void whenFindCheckingsByUserId_thenReturnCheckings() {
        Collection<CheckingBasicInfo> userCheckings = generator.objects(CheckingBasicInfo.class, 6)
                .collect(Collectors.toList());
        when(usersService.findCheckingsUserById(any())).thenReturn(userCheckings);

        Collection<CheckingBasicInfo> response = usersController.findCheckingsUserById(1).getBody();

        assertEquals(userCheckings, response);
        assertEquals(6, response.size());
    }

    @Test
    void whenFindRequestsByUserId_thenReturnRequests() {
        Collection<RequestBasicInfo> userRequests = generator.objects(RequestBasicInfo.class, 7)
                .collect(Collectors.toList());
        when(usersService.findRequestsUserById(any())).thenReturn(userRequests);

        Collection<RequestBasicInfo> response = usersController.findRequestsUserById(1).getBody();

        assertEquals(userRequests, response);
        assertEquals(7, response.size());
    }

}

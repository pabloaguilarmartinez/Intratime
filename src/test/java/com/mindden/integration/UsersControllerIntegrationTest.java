package com.mindden.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindden.BackendApplication;
import com.mindden.controller.UsersController;
import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.service.UsersService;
import lombok.SneakyThrows;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.mindden.constants.Constants.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = BackendApplication.class)
@WebMvcTest(UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerIntegrationTest {
    private final EasyRandom generator = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersService usersService;

    @SneakyThrows
    @Test
    void whenFindAll_thenReturnAll() {
        Collection<UserInfo> users = generator.objects(UserInfo.class, 3).collect(Collectors.toList());
        when(usersService.findAll()).thenReturn(users);

        mockMvc.perform(get(USERS_URI).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(users))))
                .andExpect(jsonPath("$", hasSize(users.size())))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @SneakyThrows
    @Test
    void whenFindCheckingsByUserId_thenReturnCheckings() {
        Collection<CheckingBasicInfo> userCheckings = generator.objects(CheckingBasicInfo.class, 4).collect(Collectors.toList());
        Integer userId = generator.nextObject(Integer.class);
        String userIdUri = "/".concat(userId.toString());
        when(usersService.findCheckingsUserById(any())).thenReturn(userCheckings);

        mockMvc.perform(
                        get(USERS_URI.concat(userIdUri).concat(CHECKINGS_URI)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[3].id").value(userCheckings.stream().skip(3).findFirst().get().getId()))
                .andExpect(jsonPath("$.[3].date").value(userCheckings.stream().skip(3).findFirst().get().getDate().toString()))
                .andExpect(jsonPath("$.[3].type").value(userCheckings.stream().skip(3).findFirst().get().getType().toString()))
                .andExpect(jsonPath("$", hasSize(userCheckings.size())))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @SneakyThrows
    @Test
    void whenFindRequestsByUserId_thenReturnRequests() {
        Collection<RequestBasicInfo> userRequests = generator.objects(RequestBasicInfo.class, 4).collect(Collectors.toList());
        Integer userId = generator.nextObject(Integer.class);
        String userIdUri = "/".concat(userId.toString());
        when(usersService.findRequestsUserById(any())).thenReturn(userRequests);

        mockMvc.perform(
                        get(USERS_URI.concat(userIdUri).concat(REQUESTS_URI)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[2].id").value(userRequests.stream().skip(2).findFirst().get().getId()))
                .andExpect(jsonPath("$.[2].date").value(userRequests.stream().skip(2).findFirst().get().getDate().toString()))
                .andExpect(jsonPath("$.[2].description").value(userRequests.stream().skip(2).findFirst().get().getDescription()))
                .andExpect(jsonPath("$.[2].checkingType").value(userRequests.stream().skip(2).findFirst().get().getCheckingType().toString()))
                .andExpect(jsonPath("$.[1].status").value(userRequests.stream().skip(1).findFirst().get().getStatus().toString()))
                .andExpect(jsonPath("$", hasSize(userRequests.size())))
                .andExpect(jsonPath("$").isNotEmpty());

    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

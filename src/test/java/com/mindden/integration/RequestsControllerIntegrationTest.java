package com.mindden.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindden.BackendApplication;
import com.mindden.controller.RequestsController;
import com.mindden.model.RequestInfo;
import com.mindden.service.RequestsService;
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

import static com.mindden.constants.Constants.REQUESTS_URI;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = BackendApplication.class)
@WebMvcTest(RequestsController.class)
@AutoConfigureMockMvc(addFilters = false)
class RequestsControllerIntegrationTest {
    private final EasyRandom generator = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestsService requestsService;

    @SneakyThrows
    @Test
    void whenFindAll_thenReturnAll() {
        Collection<RequestInfo> requests = generator.objects(RequestInfo.class, 2).collect(Collectors.toList());
        when(requestsService.findAll()).thenReturn(requests);

        mockMvc.perform(get(REQUESTS_URI).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(requests.stream().findFirst().get().getId()))
                .andExpect(jsonPath("$.[0].status").value(requests.stream().findFirst().get().getStatus().toString()))
                .andExpect(jsonPath("$.[0].checkingType").value(requests.stream().findFirst().get().getCheckingType().toString()))
                .andExpect(jsonPath("$.[1].id").value(requests.stream().skip(1).findFirst().get().getId()))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(requests.size())));
    }

    @SneakyThrows
    @Test
    void whenCreateRequest_thenReturnCreatedRequest() {
        RequestInfo request = generator.nextObject(RequestInfo.class);
        when(requestsService.create(request)).thenReturn(request);

        mockMvc.perform(post(REQUESTS_URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
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

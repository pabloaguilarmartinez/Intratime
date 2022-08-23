package com.mindden.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindden.BackendApplication;
import com.mindden.controller.CheckingsController;
import com.mindden.model.CheckingInfo;
import com.mindden.service.CheckingsService;
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
import java.util.List;

import static com.mindden.constants.Constants.CHECKINGS_URI;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = BackendApplication.class)
@WebMvcTest(CheckingsController.class)
@AutoConfigureMockMvc(addFilters = false)
class CheckingsControllerIntegrationTest {
    private final EasyRandom generator = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CheckingsService checkingsService;

    @SneakyThrows
    @Test
    void whenFindAll_thenReturnAll() {
        CheckingInfo checkingInfo = generator.nextObject(CheckingInfo.class);
        Collection<CheckingInfo> checkings = List.of(checkingInfo);
        when(checkingsService.findAll()).thenReturn(checkings);

        mockMvc.perform(get(CHECKINGS_URI).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(checkings.stream().findFirst().get().getId()))
                .andExpect(jsonPath("$.[0].type").value(checkings.stream().findFirst().get().getType().toString()))
                .andExpect(jsonPath("$.[0].date").value(checkings.stream().findFirst().get().getDate().toString()))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(checkings.size())));
    }

    @SneakyThrows
    @Test
    void whenCreateRequest_thenReturnCreatedRequest() {
        CheckingInfo checking = generator.nextObject(CheckingInfo.class);
        when(checkingsService.create(checking)).thenReturn(checking);

        mockMvc.perform(post(CHECKINGS_URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(checking)))
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

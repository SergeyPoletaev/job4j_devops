package ru.job4j.devops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.devops.controllers.ResultController;
import ru.job4j.devops.models.CalcResult;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.service.ResultService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ResultController.class)
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    @SuppressWarnings("removal")
    private ResultService resultService;

    @Test
    public void whenGetAllThenOk() throws Exception {
        when(resultService.findAll()).thenReturn(List.of());
        MvcResult mvcResult = mockMvc.perform(get("/calc/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualTo("[]");
    }

    @Test
    public void whenPostThenOk() throws Exception {
        when(resultService.add(new TwoArgs(1, 2))).thenReturn(CalcResult.builder().result(3).build());
        MvcResult mvcResult = mockMvc.perform(post("/calc/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TwoArgs(1, 2))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        CalcResult actualCalcResult = objectMapper.readValue(actualResponseBody, CalcResult.class);
        assertThat(actualCalcResult.getResult()).isEqualTo(3);
    }
}

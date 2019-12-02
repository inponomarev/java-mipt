package edu.phystech.jdbcdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.service.dao.ConferenceDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControllerTestConfiguration.class)
@AutoConfigureMockMvc
class ConferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ConferenceController controller;

    @MockBean
    ConferenceDao conferenceDao;

    @Test
    void saveConference() throws Exception {

        Conference c = new Conference(1, "FOO");
        ResultActions foo = mockMvc.perform(
                MockMvcRequestBuilders.put(ConferenceController.URI)
                        .param("id", Integer.toString(c.getId()))
                        .param("name", c.getName())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk());
        Mockito.verify(conferenceDao, Mockito.times(1))
                .saveConferences(Collections.singleton(c));
    }
}
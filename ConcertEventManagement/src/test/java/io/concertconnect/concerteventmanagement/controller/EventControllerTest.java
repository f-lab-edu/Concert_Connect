package io.concertconnect.concerteventmanagement.controller;

import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class EventControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void testRetrieveAllEvents() throws Exception {
        Event event = new Event();
        event.setId(1);
        event.setName("Test Event");

        when(eventService.retrieveAllEvents()).thenReturn(Arrays.asList(event));

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRetrieveEvent() throws Exception {
        Event event = new Event();
        event.setId(1);
        event.setName("Test Event");

        when(eventService.retrieveEvent(1)).thenReturn(event);

        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Event"));
    }
    /*
    삭제한 이벤트의 id값이 null이기때문에 npe오류 반환
    */

}

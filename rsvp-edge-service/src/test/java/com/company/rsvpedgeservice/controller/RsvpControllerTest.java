package com.company.rsvpedgeservice.controller;

import com.company.rsvpedgeservice.model.Rsvp;
import com.company.rsvpedgeservice.service.RsvpServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RsvpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RsvpServiceLayer serviceLayer;

    private <T> String writeToJson(T obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }

    private static Rsvp valid, validOutput;

    static {
        valid = new Rsvp();
        valid.setTotalAttending(4);
        valid.setPhoneNumber("1112223333");
        valid.setGuestName("Person");

        validOutput = new Rsvp();
        validOutput.setTotalAttending(4);
        validOutput.setPhoneNumber("1112223333");
        validOutput.setGuestName("Person");
        validOutput.setRsvpId(1);
    }

    @Test
    public void test_saveRsvp_WillPassIfNumberIsValid() throws Exception {

        String input = writeToJson(valid);
        String output = writeToJson(validOutput);

        when(serviceLayer.saveRsvp(valid)).thenReturn(validOutput);

        mockMvc.perform(post("/rsvps")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(input))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output));

    }

    @Test
    public void test_saveRsvp_WillFailIfOrderIsTooHigh() throws Exception {
        valid.setTotalAttending(6);

        when(serviceLayer.saveRsvp(valid)).thenThrow(new IllegalArgumentException("Number was too high, please try again"));

        mockMvc.perform(post("/rsvps")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(valid)))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void test_saveRsvp_WillFailIfOrderIsTooLow() throws Exception {
        valid.setTotalAttending(0);

        when(serviceLayer.saveRsvp(valid)).thenThrow(new IllegalArgumentException("Number must be greater that zero, please try again"));

        mockMvc.perform(post("/rsvps")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(writeToJson(valid)))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void test_SaveRsvp_WillFailIfPropertiesIsMissing() throws Exception {

        Rsvp rsvp = new Rsvp();

        mockMvc.perform(post("/rsvps")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeToJson(rsvp)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_getAllRsvps_WillReturnAListOfRSVP() throws Exception {
        List<Rsvp> all = new ArrayList<>();
        all.add(validOutput);

        mockMvc.perform(get("/rsvps"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(writeToJson(all)));

    }

}
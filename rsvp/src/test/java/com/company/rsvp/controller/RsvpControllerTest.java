package com.company.rsvp.controller;

import com.company.rsvp.RsvpDao;
import com.company.rsvp.model.Rsvp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RsvpControllerTest {

    @MockBean
    RsvpDao dao;

    @Autowired
    MockMvc mvc;

    public String convToJson(Object obj) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    Rsvp inputRsvp = new Rsvp();
    Rsvp outputRsvp = new Rsvp();
    List<Rsvp> rsvpList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        inputRsvp.setGuestName("Patrick");
        inputRsvp.setPhoneNumber("1112223333");
        inputRsvp.setTotalAttending(5);

        outputRsvp.setRsvpId(1);
        outputRsvp.setGuestName("Patrick");
        outputRsvp.setPhoneNumber("1112223333");
        outputRsvp.setTotalAttending(5);

        rsvpList.add(outputRsvp);

    }

    @Test
    public void createRsvp() throws Exception {

        when(dao.save(inputRsvp)).thenReturn(outputRsvp);

        mvc.perform(post("/rsvps")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(convToJson(inputRsvp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(convToJson(outputRsvp)));
    }

    @Test
    public void getRsvpById() throws Exception{
        when(dao.getOne(1)).thenReturn(outputRsvp);

        mvc.perform(get("/rsvps/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(convToJson(outputRsvp)));


    }

    @Test
    public void getAllRsvps() throws Exception {
        when(dao.findAll()).thenReturn(rsvpList);

        mvc.perform(get("/rsvps"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(convToJson(rsvpList)));
    }

    @Test
    public void updateRsvp() throws Exception{

//        when(dao.save(outputRsvp)).then()

        mvc.perform(put("/rsvps")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(convToJson(outputRsvp)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRsvp() throws Exception{
        mvc.perform(delete("/rsvps/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
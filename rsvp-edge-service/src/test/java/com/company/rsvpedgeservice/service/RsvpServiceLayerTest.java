package com.company.rsvpedgeservice.service;

import com.company.rsvpedgeservice.model.Rsvp;
import com.company.rsvpedgeservice.util.feign.RsvpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RsvpServiceLayerTest {

    @InjectMocks
    private RsvpServiceLayer service;

    @Mock
    private RsvpClient client;

    private Rsvp testRsvp, testRsvp2;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        setupRsvpClientMock();
    }

    private void setupRsvpClientMock() {
        testRsvp = new Rsvp();
        testRsvp.setGuestName("Person");
        testRsvp.setPhoneNumber("1112223333");
        testRsvp.setTotalAttending(4);

        Rsvp outputRsvp = new Rsvp();
        outputRsvp.setGuestName("Person");
        outputRsvp.setPhoneNumber("1112223333");
        outputRsvp.setTotalAttending(4);
        outputRsvp.setRsvpId(1);

        doReturn(outputRsvp).when(client).save(testRsvp);

    }

    @Test
    public void test_saveRsvp_WillCallClientWithValidAttendingNumber() {
        Rsvp verify = service.saveRsvp(testRsvp);
        assertNotEquals(0, verify.getRsvpId());
        assertTrue(verify.getTotalAttending() < 5);
    }

    @Test
    public void test_saveRsvp_WillFailIfNumberIsTooHigh() {
        Rsvp testFail = new Rsvp();
        testFail.setTotalAttending(5);
        try {
            service.saveRsvp(testFail);
        } catch (IllegalArgumentException e) {
            assertEquals("Number was too high, please try again", e.getMessage());
        }
    }

    @Test
    public void test_saveRsvp_WillFailIfNumberIsTooLow() {
        Rsvp testFail = new Rsvp();
        testFail.setTotalAttending(0);
        try {
            service.saveRsvp(testFail);
        } catch (IllegalArgumentException e) {
            assertEquals("Must use a number greater than zero, please try again", e.getMessage());
        }
    }
}
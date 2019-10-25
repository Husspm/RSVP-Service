package com.company.rsvpedgeservice.service;

import com.company.rsvpedgeservice.model.Rsvp;
import com.company.rsvpedgeservice.util.feign.RsvpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RsvpServiceLayer {
    @Autowired
    private RsvpClient client;

    public Rsvp saveRsvp(Rsvp rsvp) {
        if (rsvp.getTotalAttending() > 0  && rsvp.getTotalAttending() < 5) {
            return client.save(rsvp);
        } else {
            if (rsvp.getTotalAttending() <= 0) {
                throw new IllegalArgumentException("Must use a number greater than zero, please try again");
            } else {
                throw new IllegalArgumentException("Number was too high, please try again");
            }
        }
    }

    public List<Rsvp> getAllRsvp() {
        return client.getAll();
    }
}

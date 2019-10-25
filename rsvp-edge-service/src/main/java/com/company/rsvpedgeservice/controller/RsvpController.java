package com.company.rsvpedgeservice.controller;

import com.company.rsvpedgeservice.model.Rsvp;
import com.company.rsvpedgeservice.service.RsvpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RsvpController {
    @Autowired
    private RsvpServiceLayer service;

    /**
     * Edge Services
     * @param rsvp
     * @return
     */

    @PostMapping("rsvps")
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp saveRsvp(@RequestBody Rsvp rsvp) {
        return service.saveRsvp(rsvp);
    }

}

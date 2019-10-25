package com.company.rsvpedgeservice.controller;

import com.company.rsvpedgeservice.model.Rsvp;
import com.company.rsvpedgeservice.service.RsvpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rsvps")
public class RsvpController {
    @Autowired
    private RsvpServiceLayer service;

    /**
     * Edge Services
     * @param rsvp
     * @return
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp saveRsvp(@RequestBody @Valid Rsvp rsvp) {
        return service.saveRsvp(rsvp);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Rsvp> getAllRsvp(@RequestBody @Valid Rsvp rsvp) {
        return service.getAllRsvp();
    }


}

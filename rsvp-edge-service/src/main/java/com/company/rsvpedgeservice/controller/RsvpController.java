package com.company.rsvpedgeservice.controller;

import com.company.rsvpedgeservice.service.RsvpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RsvpController {
    @Autowired
    private RsvpServiceLayer service;


}

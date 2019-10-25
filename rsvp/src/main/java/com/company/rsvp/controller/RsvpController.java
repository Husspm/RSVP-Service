package com.company.rsvp.controller;

import com.company.rsvp.RsvpDao;
import com.company.rsvp.model.Rsvp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RsvpController {

    @Autowired
    RsvpDao dao;

    @PostMapping("/rsvps")
    public Rsvp createRsvp(@RequestBody Rsvp rsvp){
        return dao.save(rsvp);
    }

    @GetMapping("/rsvps/{rsvpId}")
    public Rsvp getRsvpById(@PathVariable int rsvpId){
        return dao.getOne(rsvpId);
    }

    @GetMapping("/rsvps")
    public List<Rsvp> getAllRsvps(){
        return dao.findAll();
    }

    @PutMapping("/rsvps")
    public void updateRsvp(@RequestBody Rsvp rsvp){
        dao.save(rsvp);
    }

    @DeleteMapping("/rsvps/{rsvpId}")
    public void deleteRsvp(@PathVariable int rsvpId){
        dao.deleteById(rsvpId);
    }

}

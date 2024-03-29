package com.company.rsvpedgeservice.util.feign;

import com.company.rsvpedgeservice.model.Rsvp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("rsvp-service")
public interface RsvpClient {
    @PostMapping("/rsvps")
    Rsvp save(@RequestBody Rsvp testRsvp);
    @GetMapping("/rsvps")
    List<Rsvp> getAll();
}

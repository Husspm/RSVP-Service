package com.company.rsvpedgeservice.util.feign;

import com.company.rsvpedgeservice.model.Rsvp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("rsvp-service")
public interface RsvpClient {
    @GetMapping("/rsvps")
    Rsvp save(@RequestBody Rsvp testRsvp);
}

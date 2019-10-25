package com.company.rsvp;

import com.company.rsvp.model.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsvpDao extends JpaRepository<Rsvp, Integer> {
}

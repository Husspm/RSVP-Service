package com.company.rsvp.dao;

import com.company.rsvp.model.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsvpDao extends JpaRepository<Rsvp, Integer> {
}

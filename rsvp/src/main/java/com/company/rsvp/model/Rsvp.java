package com.company.rsvp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@JsonIgnoreProperties()
public class Rsvp {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rsvpId;
    @NotNull
    private String guestName;
    @NotNull
    @PositiveOrZero
    @Max(5)
    private Integer totalAttending;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    public Integer getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(Integer rsvpId) {
        this.rsvpId = rsvpId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Integer getTotalAttending() {
        return totalAttending;
    }

    public void setTotalAttending(Integer totalAttending) {
        this.totalAttending = totalAttending;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rsvp rsvp = (Rsvp) o;
        return Objects.equals(rsvpId, rsvp.rsvpId) &&
                guestName.equals(rsvp.guestName) &&
                totalAttending.equals(rsvp.totalAttending) &&
                phoneNumber.equals(rsvp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rsvpId, guestName, totalAttending, phoneNumber);
    }
}

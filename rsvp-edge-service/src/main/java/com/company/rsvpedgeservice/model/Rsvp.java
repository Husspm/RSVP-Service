package com.company.rsvpedgeservice.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Rsvp {
    private int rsvpId;
    @NotEmpty(message = "Must enter a name in order to RSVP")
    private String guestName;
    private int totalAttending;
    @NotEmpty(message = "Please supply a phone number so we can contact you if needed")
    private String phoneNumber;

    public int getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(int id) {
        this.rsvpId = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getTotalAttending() {
        return totalAttending;
    }

    public void setTotalAttending(int totalAttending) {
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
        return rsvpId == rsvp.rsvpId &&
                totalAttending == rsvp.totalAttending &&
                Objects.equals(guestName, rsvp.guestName) &&
                Objects.equals(phoneNumber, rsvp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rsvpId, guestName, totalAttending, phoneNumber);
    }
}

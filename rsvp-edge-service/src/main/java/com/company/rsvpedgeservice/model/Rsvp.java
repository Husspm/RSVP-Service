package com.company.rsvpedgeservice.model;

import java.util.Objects;

public class Rsvp {
    private int rsvpId;
    private String guestName;
    private int totalAttending;
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
                guestName.equals(rsvp.guestName) &&
                phoneNumber.equals(rsvp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rsvpId, guestName, totalAttending, phoneNumber);
    }
}

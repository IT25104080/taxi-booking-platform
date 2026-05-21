package com.example.viberide_taxibookingsystem.ride_booking;

import java.time.LocalDate;

public abstract class Booking implements Bookable {

    private String bookingId;
    private String passengerId;
    private String pickupLocation;
    private String dropoffLocation;
    private double distanceKm;
    private String status;
    private LocalDate bookingDate;

    public Booking(String bookingId, String passengerId, String pickupLocation,
                   String dropoffLocation, double distanceKm, String status,
                   LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.distanceKm = distanceKm;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public abstract String toFileString();

    @Override
    public String toString() {
        return bookingId + " | " + pickupLocation + " -> " + dropoffLocation
               + " | " + status + " | " + bookingDate;
    }
}

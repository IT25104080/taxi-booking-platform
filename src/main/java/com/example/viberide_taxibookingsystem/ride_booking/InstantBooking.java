package com.example.viberide_taxibookingsystem.ride_booking;

import java.time.LocalDate;

public class InstantBooking extends Booking {

    private double surgeMultiplier;
    private static final double BASE_RATE_PER_KM = 80.0;

    public InstantBooking(String bookingId, String passengerId, String pickupLocation,
                          String dropoffLocation, double distanceKm, String status,
                          LocalDate bookingDate, double surgeMultiplier) {
        super(bookingId, passengerId, pickupLocation, dropoffLocation,
              distanceKm, status, bookingDate);
        this.surgeMultiplier = surgeMultiplier;
    }

    public double getSurgeMultiplier() { return surgeMultiplier; }
    public void setSurgeMultiplier(double surgeMultiplier) { this.surgeMultiplier = surgeMultiplier; }

    @Override
    public double calculateFare() {
        return getDistanceKm() * BASE_RATE_PER_KM * surgeMultiplier;
    }

    @Override
    public String getBookingDetails() {
        return "Instant Ride | " + getPickupLocation() + " -> " + getDropoffLocation()
               + " | Fare: Rs." + String.format("%.2f", calculateFare())
               + " (Surge: " + surgeMultiplier + "x)";
    }

    @Override
    public String toFileString() {
        return "INSTANT," + getBookingId() + "," + getPassengerId() + ","
               + getPickupLocation() + "," + getDropoffLocation() + ","
               + getDistanceKm() + "," + getStatus() + "," + getBookingDate() + ","
               + surgeMultiplier;
    }
}

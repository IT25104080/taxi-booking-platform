package com.example.viberide_taxibookingsystem.ride_booking;

import java.time.LocalDate;

public class ScheduledBooking extends Booking {

    private String scheduledTime;
    private boolean reminderSent;
    private static final double BASE_RATE_PER_KM = 80.0;
    private static final double SCHEDULED_DISCOUNT = 0.9;

    public ScheduledBooking(String bookingId, String passengerId, String pickupLocation,
                            String dropoffLocation, double distanceKm, String status,
                            LocalDate bookingDate, String scheduledTime, boolean reminderSent) {
        super(bookingId, passengerId, pickupLocation, dropoffLocation,
              distanceKm, status, bookingDate);
        this.scheduledTime = scheduledTime;
        this.reminderSent = reminderSent;
    }

    public String getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(String scheduledTime) { this.scheduledTime = scheduledTime; }

    public boolean isReminderSent() { return reminderSent; }
    public void setReminderSent(boolean reminderSent) { this.reminderSent = reminderSent; }

    @Override
    public double calculateFare() {
        return getDistanceKm() * BASE_RATE_PER_KM * SCHEDULED_DISCOUNT;
    }

    @Override
    public String getBookingDetails() {
        return "Scheduled Ride @ " + scheduledTime + " | " + getPickupLocation()
               + " -> " + getDropoffLocation()
               + " | Fare: Rs." + String.format("%.2f", calculateFare())
               + " (10% off)";
    }

    @Override
    public String toFileString() {
        return "SCHEDULED," + getBookingId() + "," + getPassengerId() + ","
               + getPickupLocation() + "," + getDropoffLocation() + ","
               + getDistanceKm() + "," + getStatus() + "," + getBookingDate() + ","
               + scheduledTime + "," + reminderSent;
    }
}

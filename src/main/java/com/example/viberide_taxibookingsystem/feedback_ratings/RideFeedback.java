package com.example.viberide_taxibookingsystem.feedback_ratings;

import java.time.LocalDate;

public class RideFeedback extends Feedback {

    private String rideId;
    private String driverName;
    private int driverRating;
    private int punctualityRating;
    private int vehicleRating;

    public RideFeedback(String feedbackId, String customerName, int rating,
                        String comments, LocalDate date, String rideId,
                        String driverName, int driverRating,
                        int punctualityRating, int vehicleRating) {
        super(feedbackId, customerName, rating, comments, date);
        this.rideId = rideId;
        this.driverName = driverName;
        this.driverRating = driverRating;
        this.punctualityRating = punctualityRating;
        this.vehicleRating = vehicleRating;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public int getDriverRating() { return driverRating; }
    public void setDriverRating(int driverRating) { this.driverRating = driverRating; }
    public int getPunctualityRating() { return punctualityRating; }
    public void setPunctualityRating(int punctualityRating) { this.punctualityRating = punctualityRating; }
    public int getVehicleRating() { return vehicleRating; }
    public void setVehicleRating(int vehicleRating) { this.vehicleRating = vehicleRating; }

    @Override
    public double calculateRating() {
        return (driverRating * 0.5) + (punctualityRating * 0.3) + (vehicleRating * 0.2);
    }

    @Override
    public String getSummary() {
        return "Ride " + rideId + " | Driver: " + driverName
               + " | Score: " + String.format("%.1f", calculateRating())
               + "/5 | " + getComments();
    }

    @Override
    public String toFileString() {
        return "RIDE," + getFeedbackId() + "," + getCustomerName() + ","
               + getRating() + "," + getComments() + "," + getDate() + ","
               + rideId + "," + driverName + "," + driverRating + ","
               + punctualityRating + "," + vehicleRating;
    }
}

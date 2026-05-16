package com.example.viberide_taxibookingsystem.feedback_ratings;

import java.time.LocalDate;

public class PlatformFeedback extends Feedback {

    private String category;

    public PlatformFeedback(String feedbackId, String customerName, int rating,
                            String comments, LocalDate date, String category) {
        super(feedbackId, customerName, rating, comments, date);
        this.category = category;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public double calculateRating() { return getRating(); }

    @Override
    public String getSummary() {
        return "[" + category + "] " + getCustomerName()
               + " rated " + getRating() + "/5 | " + getComments();
    }

    @Override
    public String toFileString() {
        return "PLATFORM," + getFeedbackId() + "," + getCustomerName() + ","
               + getRating() + "," + getComments() + "," + getDate() + ","
               + category;
    }
}

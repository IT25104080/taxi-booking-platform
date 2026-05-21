package com.example.viberide_taxibookingsystem.feedback_ratings;

import java.time.LocalDate;

public abstract class Feedback implements Rateable {

    private String feedbackId;
    private String customerName;
    private int rating;
    private String comments;
    private LocalDate date;

    public Feedback(String feedbackId, String customerName, int rating,
                    String comments, LocalDate date) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.rating = rating;
        this.comments = comments;
        this.date = date;
    }

    public String getFeedbackId() { return feedbackId; }
    public void setFeedbackId(String feedbackId) { this.feedbackId = feedbackId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public abstract String toFileString();

    @Override
    public String toString() {
        return feedbackId + " | " + customerName + " | " + rating
               + " | " + comments + " | " + date;
    }
}

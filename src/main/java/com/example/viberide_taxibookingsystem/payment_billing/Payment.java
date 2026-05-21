package com.example.viberide_taxibookingsystem.payment_billing;

import java.time.LocalDate;

public abstract class Payment implements Processable {

    private String paymentId;
    private String bookingId;
    private double amount;
    private String status;
    private LocalDate paymentDate;

    public Payment(String paymentId, String bookingId, double amount,
                   String status, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public abstract String toFileString();

    @Override
    public String toString() {
        return paymentId + " | Booking: " + bookingId + " | Rs." + amount + " | " + status;
    }
}

package com.example.viberide_taxibookingsystem.payment_billing;

import java.time.LocalDate;

public class CashPayment extends Payment {

    private double receivedAmount;

    public CashPayment(String paymentId, String bookingId, double amount,
                       String status, LocalDate paymentDate, double receivedAmount) {
        super(paymentId, bookingId, amount, status, paymentDate);
        this.receivedAmount = receivedAmount;
    }

    public double getReceivedAmount() { return receivedAmount; }
    public void setReceivedAmount(double receivedAmount) { this.receivedAmount = receivedAmount; }

    public double getChangeGiven() { return receivedAmount - getAmount(); }

    @Override
    public double processPayment() {
        if (receivedAmount >= getAmount()) {
            setStatus("Completed");
            return getChangeGiven();
        }
        setStatus("Insufficient");
        return getAmount() - receivedAmount;
    }

    @Override
    public String getReceipt() {
        return "Cash Payment | Booking: " + getBookingId()
               + " | Amount: Rs." + String.format("%.2f", getAmount())
               + " | Received: Rs." + String.format("%.2f", receivedAmount)
               + " | Change: Rs." + String.format("%.2f", getChangeGiven());
    }

    @Override
    public String toFileString() {
        return "CASH," + getPaymentId() + "," + getBookingId() + ","
               + getAmount() + "," + getStatus() + "," + getPaymentDate() + ","
               + receivedAmount;
    }
}

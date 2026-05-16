package com.example.viberide_taxibookingsystem.payment_billing;

import java.time.LocalDate;

public class CardPayment extends Payment {

    private String cardLastFour;
    private String transactionRef;

    public CardPayment(String paymentId, String bookingId, double amount,
                       String status, LocalDate paymentDate,
                       String cardLastFour, String transactionRef) {
        super(paymentId, bookingId, amount, status, paymentDate);
        this.cardLastFour = cardLastFour;
        this.transactionRef = transactionRef;
    }

    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String cardLastFour) { this.cardLastFour = cardLastFour; }
    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }

    @Override
    public double processPayment() {
        setStatus("Completed");
        return getAmount();
    }

    @Override
    public String getReceipt() {
        return "Card Payment | Booking: " + getBookingId()
               + " | Amount: Rs." + String.format("%.2f", getAmount())
               + " | Card: ****" + cardLastFour
               + " | Ref: " + transactionRef;
    }

    @Override
    public String toFileString() {
        return "CARD," + getPaymentId() + "," + getBookingId() + ","
               + getAmount() + "," + getStatus() + "," + getPaymentDate() + ","
               + cardLastFour + "," + transactionRef;
    }
}

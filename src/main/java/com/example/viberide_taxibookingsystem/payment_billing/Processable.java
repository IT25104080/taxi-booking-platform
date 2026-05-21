package com.example.viberide_taxibookingsystem.payment_billing;

public interface Processable {
    double processPayment();
    String getReceipt();
}

package com.example.viberide_taxibookingsystem.payment_billing;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentFileHandler {

    private static final String FILE_PATH = "data/payments.txt";

    public static void addPayment(Payment payment) throws IOException {
        new File("data").mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(payment.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Payment> getAllPayments() throws IOException {
        List<Payment> paymentList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return paymentList;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 2) continue;
            String type = parts[0];

            if (type.equals("CASH") && parts.length >= 7) {
                CashPayment cp = new CashPayment(
                    parts[1], parts[2], Double.parseDouble(parts[3]),
                    parts[4], LocalDate.parse(parts[5]),
                    Double.parseDouble(parts[6])
                );
                paymentList.add(cp);
            } else if (type.equals("CARD") && parts.length >= 8) {
                CardPayment cdp = new CardPayment(
                    parts[1], parts[2], Double.parseDouble(parts[3]),
                    parts[4], LocalDate.parse(parts[5]),
                    parts[6], parts[7]
                );
                paymentList.add(cdp);
            }
        }
        reader.close();
        return paymentList;
    }

    public static Payment getPaymentById(String paymentId) throws IOException {
        for (Payment p : getAllPayments()) {
            if (p.getPaymentId().equals(paymentId)) return p;
        }
        return null;
    }

    public static void updatePayment(Payment updatedPayment) throws IOException {
        List<Payment> all = getAllPayments();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Payment p : all) {
            writer.write(p.getPaymentId().equals(updatedPayment.getPaymentId())
                         ? updatedPayment.toFileString() : p.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    public static void deletePayment(String paymentId) throws IOException {
        List<Payment> all = getAllPayments();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Payment p : all) {
            if (!p.getPaymentId().equals(paymentId)) {
                writer.write(p.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }
}

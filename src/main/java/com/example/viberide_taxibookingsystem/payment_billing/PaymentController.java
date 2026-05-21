package com.example.viberide_taxibookingsystem.payment_billing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PaymentController {

    @GetMapping("/payments")
    public String viewAllPayments(Model model) throws IOException {
        List<Payment> paymentList = PaymentFileHandler.getAllPayments();
        model.addAttribute("paymentList", paymentList);
        return "payment-list";
    }

    @GetMapping("/payments/new")
    public String showPaymentForm() { return "payment-form"; }

    @PostMapping("/payments/cash")
    public String createCashPayment(
            @RequestParam String paymentId, @RequestParam String bookingId,
            @RequestParam double amount, @RequestParam String status,
            @RequestParam double receivedAmount) throws IOException {
        CashPayment cp = new CashPayment(paymentId, bookingId, amount, status,
                                          LocalDate.now(), receivedAmount);
        PaymentFileHandler.addPayment(cp);
        return "redirect:/payments";
    }

    @PostMapping("/payments/card")
    public String createCardPayment(
            @RequestParam String paymentId, @RequestParam String bookingId,
            @RequestParam double amount, @RequestParam String status,
            @RequestParam String cardLastFour, @RequestParam String transactionRef) throws IOException {
        CardPayment cdp = new CardPayment(paymentId, bookingId, amount, status,
                                           LocalDate.now(), cardLastFour, transactionRef);
        PaymentFileHandler.addPayment(cdp);
        return "redirect:/payments";
    }

    @GetMapping("/payments/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Payment payment = PaymentFileHandler.getPaymentById(id);
        if (payment instanceof CashPayment) {
            model.addAttribute("type", "CASH");
            model.addAttribute("cashPayment", (CashPayment) payment);
        } else if (payment instanceof CardPayment) {
            model.addAttribute("type", "CARD");
            model.addAttribute("cardPayment", (CardPayment) payment);
        } else {
            return "redirect:/payments";
        }
        return "payment-edit";
    }

    @PostMapping("/payments/update/cash")
    public String updateCash(
            @RequestParam String paymentId, @RequestParam String bookingId,
            @RequestParam double amount, @RequestParam String status,
            @RequestParam String date, @RequestParam double receivedAmount) throws IOException {
        CashPayment cp = new CashPayment(paymentId, bookingId, amount, status,
                                          LocalDate.parse(date), receivedAmount);
        PaymentFileHandler.updatePayment(cp);
        return "redirect:/payments";
    }

    @PostMapping("/payments/update/card")
    public String updateCard(
            @RequestParam String paymentId, @RequestParam String bookingId,
            @RequestParam double amount, @RequestParam String status,
            @RequestParam String date, @RequestParam String cardLastFour,
            @RequestParam String transactionRef) throws IOException {
        CardPayment cdp = new CardPayment(paymentId, bookingId, amount, status,
                                           LocalDate.parse(date), cardLastFour, transactionRef);
        PaymentFileHandler.updatePayment(cdp);
        return "redirect:/payments";
    }

    @GetMapping("/payments/delete/{id}")
    public String deletePayment(@PathVariable String id) throws IOException {
        PaymentFileHandler.deletePayment(id);
        return "redirect:/payments";
    }
}

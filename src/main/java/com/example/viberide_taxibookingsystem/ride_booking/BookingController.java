package com.example.viberide_taxibookingsystem.ride_booking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    @GetMapping("/bookings")
    public String viewAllBookings(Model model) throws IOException {
        List<Booking> bookingList = BookingFileHandler.getAllBookings();
        model.addAttribute("bookingList", bookingList);
        return "booking-list";
    }

    @GetMapping("/bookings/new")
    public String showBookingForm() {
        return "booking-form";
    }

    @PostMapping("/bookings/instant")
    public String createInstantBooking(
            @RequestParam String bookingId, @RequestParam String passengerId,
            @RequestParam String pickupLocation, @RequestParam String dropoffLocation,
            @RequestParam double distanceKm, @RequestParam String status,
            @RequestParam double surgeMultiplier) throws IOException {

        InstantBooking ib = new InstantBooking(bookingId, passengerId, pickupLocation,
                dropoffLocation, distanceKm, status, LocalDate.now(), surgeMultiplier);
        BookingFileHandler.addBooking(ib);
        return "redirect:/bookings";
    }

    @PostMapping("/bookings/scheduled")
    public String createScheduledBooking(
            @RequestParam String bookingId, @RequestParam String passengerId,
            @RequestParam String pickupLocation, @RequestParam String dropoffLocation,
            @RequestParam double distanceKm, @RequestParam String status,
            @RequestParam String scheduledTime) throws IOException {

        ScheduledBooking sb = new ScheduledBooking(bookingId, passengerId, pickupLocation,
                dropoffLocation, distanceKm, status, LocalDate.now(), scheduledTime, false);
        BookingFileHandler.addBooking(sb);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Booking booking = BookingFileHandler.getBookingById(id);
        if (booking instanceof InstantBooking) {
            model.addAttribute("type", "INSTANT");
            model.addAttribute("instantBooking", (InstantBooking) booking);
        } else if (booking instanceof ScheduledBooking) {
            model.addAttribute("type", "SCHEDULED");
            model.addAttribute("scheduledBooking", (ScheduledBooking) booking);
        } else {
            return "redirect:/bookings";
        }
        return "booking-edit";
    }

    @PostMapping("/bookings/update/instant")
    public String updateInstant(
            @RequestParam String bookingId, @RequestParam String passengerId,
            @RequestParam String pickupLocation, @RequestParam String dropoffLocation,
            @RequestParam double distanceKm, @RequestParam String status,
            @RequestParam String date, @RequestParam double surgeMultiplier) throws IOException {

        InstantBooking ib = new InstantBooking(bookingId, passengerId, pickupLocation,
                dropoffLocation, distanceKm, status, LocalDate.parse(date), surgeMultiplier);
        BookingFileHandler.updateBooking(ib);
        return "redirect:/bookings";
    }

    @PostMapping("/bookings/update/scheduled")
    public String updateScheduled(
            @RequestParam String bookingId, @RequestParam String passengerId,
            @RequestParam String pickupLocation, @RequestParam String dropoffLocation,
            @RequestParam double distanceKm, @RequestParam String status,
            @RequestParam String date, @RequestParam String scheduledTime,
            @RequestParam(defaultValue = "false") boolean reminderSent) throws IOException {

        ScheduledBooking sb = new ScheduledBooking(bookingId, passengerId, pickupLocation,
                dropoffLocation, distanceKm, status, LocalDate.parse(date),
                scheduledTime, reminderSent);
        BookingFileHandler.updateBooking(sb);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable String id) throws IOException {
        BookingFileHandler.deleteBooking(id);
        return "redirect:/bookings";
    }
}

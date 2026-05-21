package com.example.viberide_taxibookingsystem.ride_booking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingFileHandler {

    private static final String FILE_PATH = "data/bookings.txt";

    public static void addBooking(Booking booking) throws IOException {
        new File("data").mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(booking.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Booking> getAllBookings() throws IOException {
        List<Booking> bookingList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return bookingList;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 2) continue;
            String type = parts[0];

            if (type.equals("INSTANT") && parts.length >= 9) {
                InstantBooking ib = new InstantBooking(
                    parts[1], parts[2], parts[3], parts[4],
                    Double.parseDouble(parts[5]), parts[6],
                    LocalDate.parse(parts[7]), Double.parseDouble(parts[8])
                );
                bookingList.add(ib);
            } else if (type.equals("SCHEDULED") && parts.length >= 10) {
                ScheduledBooking sb = new ScheduledBooking(
                    parts[1], parts[2], parts[3], parts[4],
                    Double.parseDouble(parts[5]), parts[6],
                    LocalDate.parse(parts[7]), parts[8],
                    Boolean.parseBoolean(parts[9])
                );
                bookingList.add(sb);
            }
        }
        reader.close();
        return bookingList;
    }

    public static Booking getBookingById(String bookingId) throws IOException {
        for (Booking b : getAllBookings()) {
            if (b.getBookingId().equals(bookingId)) return b;
        }
        return null;
    }

    public static void updateBooking(Booking updatedBooking) throws IOException {
        List<Booking> all = getAllBookings();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Booking b : all) {
            writer.write(b.getBookingId().equals(updatedBooking.getBookingId())
                         ? updatedBooking.toFileString() : b.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    public static void deleteBooking(String bookingId) throws IOException {
        List<Booking> all = getAllBookings();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Booking b : all) {
            if (!b.getBookingId().equals(bookingId)) {
                writer.write(b.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }
}

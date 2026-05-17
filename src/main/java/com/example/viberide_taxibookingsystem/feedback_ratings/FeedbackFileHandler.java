package com.example.viberide_taxibookingsystem.feedback_ratings;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeedbackFileHandler {

    private static final String FILE_PATH = "data/feedbacks.txt";

    public static void addFeedback(Feedback feedback) throws IOException {
        new File("data").mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(feedback.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Feedback> getAllFeedbacks() throws IOException {
        List<Feedback> feedbackList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return feedbackList;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 2) continue;
            String type = parts[0];

            if (type.equals("RIDE") && parts.length >= 11) {
                RideFeedback rf = new RideFeedback(
                    parts[1], parts[2], Integer.parseInt(parts[3]),
                    parts[4], LocalDate.parse(parts[5]),
                    parts[6], parts[7],
                    Integer.parseInt(parts[8]),
                    Integer.parseInt(parts[9]),
                    Integer.parseInt(parts[10])
                );
                feedbackList.add(rf);
            } else if (type.equals("PLATFORM") && parts.length >= 7) {
                PlatformFeedback pf = new PlatformFeedback(
                    parts[1], parts[2], Integer.parseInt(parts[3]),
                    parts[4], LocalDate.parse(parts[5]),
                    parts[6]
                );
                feedbackList.add(pf);
            }
        }
        reader.close();
        return feedbackList;
    }

    public static Feedback getFeedbackById(String feedbackId) throws IOException {
        for (Feedback f : getAllFeedbacks()) {
            if (f.getFeedbackId().equals(feedbackId)) return f;
        }
        return null;
    }

    public static void updateFeedback(Feedback updatedFeedback) throws IOException {
        List<Feedback> all = getAllFeedbacks();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Feedback f : all) {
            writer.write(f.getFeedbackId().equals(updatedFeedback.getFeedbackId())
                         ? updatedFeedback.toFileString() : f.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    public static void deleteFeedback(String feedbackId) throws IOException {
        List<Feedback> all = getAllFeedbacks();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Feedback f : all) {
            if (!f.getFeedbackId().equals(feedbackId)) {
                writer.write(f.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }
}

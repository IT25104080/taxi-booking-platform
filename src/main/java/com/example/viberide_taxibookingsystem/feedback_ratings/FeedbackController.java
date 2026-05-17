package com.example.viberide_taxibookingsystem.feedback_ratings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class FeedbackController {

    @GetMapping("/feedbacks")
    public String viewAllFeedbacks(Model model) throws IOException {
        List<Feedback> feedbackList = FeedbackFileHandler.getAllFeedbacks();
        model.addAttribute("feedbackList", feedbackList);
        return "feedback-list";
    }

    @GetMapping("/feedbacks/new")
    public String showSubmitForm() { return "feedback-form"; }

    @PostMapping("/feedbacks/ride")
    public String createRideFeedback(
            @RequestParam String feedbackId, @RequestParam String customerName,
            @RequestParam int rating, @RequestParam String comments,
            @RequestParam String rideId, @RequestParam String driverName,
            @RequestParam int driverRating, @RequestParam int punctualityRating,
            @RequestParam int vehicleRating) throws IOException {

        RideFeedback rf = new RideFeedback(
            feedbackId, customerName, rating, comments,
            LocalDate.now(), rideId, driverName,
            driverRating, punctualityRating, vehicleRating
        );
        FeedbackFileHandler.addFeedback(rf);
        return "redirect:/feedbacks";
    }

    @PostMapping("/feedbacks/platform")
    public String createPlatformFeedback(
            @RequestParam String feedbackId, @RequestParam String customerName,
            @RequestParam int rating, @RequestParam String comments,
            @RequestParam String category) throws IOException {

        PlatformFeedback pf = new PlatformFeedback(
            feedbackId, customerName, rating, comments, LocalDate.now(), category
        );
        FeedbackFileHandler.addFeedback(pf);
        return "redirect:/feedbacks";
    }

    @GetMapping("/feedbacks/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Feedback feedback = FeedbackFileHandler.getFeedbackById(id);
        if (feedback instanceof RideFeedback) {
            model.addAttribute("type", "RIDE");
            model.addAttribute("rideFeedback", (RideFeedback) feedback);
        } else if (feedback instanceof PlatformFeedback) {
            model.addAttribute("type", "PLATFORM");
            model.addAttribute("platformFeedback", (PlatformFeedback) feedback);
        } else {
            return "redirect:/feedbacks";
        }
        return "feedback-edit";
    }

    @PostMapping("/feedbacks/update/ride")
    public String updateRideFeedback(
            @RequestParam String feedbackId, @RequestParam String customerName,
            @RequestParam int rating, @RequestParam String comments,
            @RequestParam String date, @RequestParam String rideId,
            @RequestParam String driverName, @RequestParam int driverRating,
            @RequestParam int punctualityRating, @RequestParam int vehicleRating) throws IOException {

        RideFeedback rf = new RideFeedback(
            feedbackId, customerName, rating, comments,
            LocalDate.parse(date), rideId, driverName,
            driverRating, punctualityRating, vehicleRating
        );
        FeedbackFileHandler.updateFeedback(rf);
        return "redirect:/feedbacks";
    }

    @PostMapping("/feedbacks/update/platform")
    public String updatePlatformFeedback(
            @RequestParam String feedbackId, @RequestParam String customerName,
            @RequestParam int rating, @RequestParam String comments,
            @RequestParam String date, @RequestParam String category) throws IOException {

        PlatformFeedback pf = new PlatformFeedback(
            feedbackId, customerName, rating, comments, LocalDate.parse(date), category
        );
        FeedbackFileHandler.updateFeedback(pf);
        return "redirect:/feedbacks";
    }

    @GetMapping("/feedbacks/delete/{id}")
    public String deleteFeedback(@PathVariable String id) throws IOException {
        FeedbackFileHandler.deleteFeedback(id);
        return "redirect:/feedbacks";
    }
}

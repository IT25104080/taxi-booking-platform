package com.example.viberide_taxibookingsystem.user_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/users")
    public String viewAllUsers(Model model) throws IOException {
        List<User> userList = UserFileHandler.getAllUsers();
        model.addAttribute("userList", userList);
        return "user-list";
    }

    @GetMapping("/users/new")
    public String showRegisterForm() {
        return "user-form";
    }

    @PostMapping("/users/passenger")
    public String createPassenger(
            @RequestParam String userId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String phone, @RequestParam String address,
            @RequestParam String preferredPayment) throws IOException {

        Passenger p = new Passenger(userId, name, email, password, phone,
                                    LocalDate.now(), address, preferredPayment);
        UserFileHandler.addUser(p);
        return "redirect:/users";
    }

    @PostMapping("/users/premium")
    public String createPremiumPassenger(
            @RequestParam String userId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String phone, @RequestParam String membershipLevel,
            @RequestParam double discountRate, @RequestParam int loyaltyPoints) throws IOException {

        PremiumPassenger pp = new PremiumPassenger(userId, name, email, password, phone,
                                                    LocalDate.now(), membershipLevel,
                                                    discountRate, loyaltyPoints);
        UserFileHandler.addUser(pp);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        User user = UserFileHandler.getUserById(id);
        if (user instanceof PremiumPassenger) {
            model.addAttribute("type", "PREMIUM");
            model.addAttribute("premiumPassenger", (PremiumPassenger) user);
        } else if (user instanceof Passenger) {
            model.addAttribute("type", "PASSENGER");
            model.addAttribute("passenger", (Passenger) user);
        } else {
            return "redirect:/users";
        }
        return "user-edit";
    }

    @PostMapping("/users/update/passenger")
    public String updatePassenger(
            @RequestParam String userId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String phone, @RequestParam String date,
            @RequestParam String address, @RequestParam String preferredPayment) throws IOException {

        Passenger p = new Passenger(userId, name, email, password, phone,
                                    LocalDate.parse(date), address, preferredPayment);
        UserFileHandler.updateUser(p);
        return "redirect:/users";
    }

    @PostMapping("/users/update/premium")
    public String updatePremiumPassenger(
            @RequestParam String userId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String phone, @RequestParam String date,
            @RequestParam String membershipLevel, @RequestParam double discountRate,
            @RequestParam int loyaltyPoints) throws IOException {

        PremiumPassenger pp = new PremiumPassenger(userId, name, email, password, phone,
                                                    LocalDate.parse(date), membershipLevel,
                                                    discountRate, loyaltyPoints);
        UserFileHandler.updateUser(pp);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable String id) throws IOException {
        UserFileHandler.deleteUser(id);
        return "redirect:/users";
    }
}

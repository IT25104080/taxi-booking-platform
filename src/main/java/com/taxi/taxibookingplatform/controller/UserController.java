package com.taxi.taxibookingplatform.controller;   // ← correct for your project

import com.taxi.taxibookingplatform.model.User;
import com.taxi.taxibookingplatform.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService = new UserService();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "User/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String password,
                           @RequestParam(defaultValue = "regular") String userType,
                           Model model) {
        try {
            userService.register(name, email, phone, password, userType);
            model.addAttribute("message", "Registration successful! Please login.");
            return "User/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "User/register";
        }
    }

    @GetMapping("/login")
    public String showLogin() {
        return "User/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User user = userService.login(email, password);
            session.setAttribute("loggedInUser", user);
            return "redirect:/user/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "User/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("user", user);
        return "User/dashboard";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("user", user);
        return "User/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam(required = false) String password,
                                HttpSession session,
                                Model model) {
        try {
            User current = (User) session.getAttribute("loggedInUser");
            User updated = userService.updateProfile(current.getUserId(), name, email, phone, password);
            session.setAttribute("loggedInUser", updated);
            model.addAttribute("message", "Profile updated!");
            model.addAttribute("user", updated);
            return "User/profile";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "User/profile";
        }
    }

    @PostMapping("/delete")
    public String deleteAccount(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("loggedInUser");
            userService.deleteUser(user.getUserId());
            session.invalidate();
            model.addAttribute("message", "Account deleted.");
            return "User/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "User/profile";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
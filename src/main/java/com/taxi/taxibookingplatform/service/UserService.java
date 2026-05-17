package com.taxi.taxibookingplatform.service;

import com.taxi.taxibookingplatform.dao.UserDAO;
import com.taxi.taxibookingplatform.model.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public User register(String name, String email, String phone, String password, String userType) throws IOException {
        // Check if email already exists
        if (userDAO.findByEmail(email) != null) {
            throw new IOException("Email already registered!");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setUserType(userType != null ? userType : "regular");
        user.setRegDate(LocalDateTime.now().format(formatter));
        userDAO.save(user);
        return user;
    }

    public User login(String email, String password) throws IOException {
        User user = userDAO.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IOException("Invalid email or password!");
        }
        return user;
    }

    public User updateProfile(String userId, String name, String email, String phone, String newPassword) throws IOException {
        User user = userDAO.findById(userId);
        if (user == null) throw new IOException("User not found");
        if (name != null && !name.isBlank()) user.setName(name);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (phone != null && !phone.isBlank()) user.setPhone(phone);
        if (newPassword != null && !newPassword.isBlank()) user.setPassword(newPassword);
        userDAO.update(user);
        return user;
    }

    public boolean deleteUser(String userId) throws IOException {
        return userDAO.delete(userId);
    }
}
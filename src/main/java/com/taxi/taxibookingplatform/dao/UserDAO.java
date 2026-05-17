package com.taxi.taxibookingplatform.dao;

import com.taxi.taxibookingplatform.common.FileHandler;
import com.taxi.taxibookingplatform.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String FILE_NAME = "users.txt";

    public void save(User user) throws IOException {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId("USR" + System.currentTimeMillis());
        }
        FileHandler.appendLine(FILE_NAME, user.toFileString());
    }

    public User findByEmail(String email) throws IOException {
        List<String> lines = FileHandler.readAllLines(FILE_NAME);
        for (String line : lines) {
            User u = User.fromFileString(line);
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public User findById(String userId) throws IOException {
        List<String> lines = FileHandler.readAllLines(FILE_NAME);
        for (String line : lines) {
            User u = User.fromFileString(line);
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

    public List<User> findAll() throws IOException {
        List<User> users = new ArrayList<>();
        for (String line : FileHandler.readAllLines(FILE_NAME)) {
            users.add(User.fromFileString(line));
        }
        return users;
    }

    public boolean update(User user) throws IOException {
        return FileHandler.updateLine(FILE_NAME, user.getUserId(), user.toFileString(), 0);
    }

    public boolean delete(String userId) throws IOException {
        return FileHandler.deleteLine(FILE_NAME, userId, 0);
    }
}
package com.taxi.taxibookingplatform.model;   // 1. Which folder this file belongs to

public class User {   // 2. Define a class called "User"

    // Fields (attributes) - these are private (encapsulation)
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String userType;   // regular, premium, admin
    private String regDate;    // registration date

    // 3. Constructor - a special method that creates a User object
    public User() {
        // empty constructor – needed for Spring
    }

    public User(String userId, String name, String email, String phone,
                String password, String userType, String regDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
        this.regDate = regDate;
    }

    // 4. Getters and Setters – the only way to access private fields (encapsulation)
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }

    // 5. Helper method to convert a User object into a string we can save to file
    public String toFileString() {
        return userId + "|" + name + "|" + email + "|" + phone + "|" +
                password + "|" + userType + "|" + regDate;
    }

    // 6. Helper method to create a User object from a line read from file
    public static User fromFileString(String line) {
        String[] parts = line.split("\\|"); // split by pipe |
        return new User(parts[0], parts[1], parts[2], parts[3],
                parts[4], parts[5], parts[6]);
    }
}
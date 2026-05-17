package com.example.viberide_taxibookingsystem.admin_management;

import java.time.LocalDate;

public abstract class Admin implements Manageable {

    private String adminId;
    private String name;
    private String email;
    private String password;
    private String department;
    private LocalDate createdDate;

    public Admin(String adminId, String name, String email, String password,
                 String department, LocalDate createdDate) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.createdDate = createdDate;
    }

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    @Override
    public String getRole() { return "ADMIN"; }

    @Override
    public String getPermissions() {
        return "Manage Bookings, Drivers, Customers and Payments";
    }

    @Override
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public String getActivityLog() {
        return "Admin: " + name + " | Department: " + department + " | Since: " + createdDate;
    }

    public String getDisplayInfo() {
        return "Admin: " + name + " | Department: " + department;
    }

    public abstract String toFileString();
}

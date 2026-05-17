package com.example.viberide_taxibookingsystem.admin_management;

import java.time.LocalDate;

public class SuperAdmin extends Admin {

    private String accessLevel;
    private int managedAdmins;

    public SuperAdmin(String adminId, String name, String email, String password,
                      String department, LocalDate createdDate,
                      String accessLevel, int managedAdmins) {
        super(adminId, name, email, password, department, createdDate);
        this.accessLevel = accessLevel;
        this.managedAdmins = managedAdmins;
    }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }

    public int getManagedAdmins() { return managedAdmins; }
    public void setManagedAdmins(int managedAdmins) { this.managedAdmins = managedAdmins; }

    @Override
    public String getRole() { return "SUPER_ADMIN"; }

    @Override
    public String getPermissions() {
        return "Full System Access | Manage All Admins | Access Level: " + accessLevel;
    }

    @Override
    public String getActivityLog() {
        return "SuperAdmin: " + getName() + " | Access: " + accessLevel
               + " | Managing " + managedAdmins + " admins";
    }

    @Override
    public String toFileString() {
        return "SUPER," + getAdminId() + "," + getName() + "," + getEmail() + ","
               + getPassword() + "," + getDepartment() + "," + getCreatedDate() + ","
               + accessLevel + "," + managedAdmins;
    }
}

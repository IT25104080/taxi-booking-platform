package com.example.viberide_taxibookingsystem.admin_management;

public interface Manageable {
    String getRole();
    String getPermissions();
    boolean authenticate(String password);
    String getActivityLog();
}

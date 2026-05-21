package com.example.viberide_taxibookingsystem.user_management;

public interface Authenticatable {
    boolean authenticate(String password);
    String getRole();
}

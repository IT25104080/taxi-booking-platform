package com.taxi.taxibookingplatform;

public interface Authenticatable {
    boolean authenticate(String password);
    String getRole();
}

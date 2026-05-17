package com.taxi.taxibookingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxiBookingPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaxiBookingPlatformApplication.class, args);
        System.out.println("✅ Taxi Booking Platform Started!");
        System.out.println("📍 Open http://localhost:8080/taxi/user/");
    }
}
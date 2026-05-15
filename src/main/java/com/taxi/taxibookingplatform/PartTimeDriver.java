package com.example.viberide_taxibookingsystem.driver_management;

import java.time.LocalDate;

public class PartTimeDriver extends Driver {

    private double hourlyRate;
    private int hoursWorked;

    public PartTimeDriver(String driverId, String name, String licenseNo, String phone,
                          String vehicleType, String vehiclePlate, String status,
                          LocalDate joinedDate, double hourlyRate, int hoursWorked) {
        super(driverId, name, licenseNo, phone, vehicleType, vehiclePlate, status, joinedDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    @Override
    public double calculateEarnings() { return hourlyRate * hoursWorked; }

    @Override
    public String getAssignmentDetails() {
        return "Part-Time: " + getName() + " | Hours: " + hoursWorked
               + " | Earnings: Rs." + String.format("%.2f", calculateEarnings())
               + " | " + (isAvailable() ? "Available" : "Unavailable");
    }

    @Override
    public String toFileString() {
        return "PARTTIME," + getDriverId() + "," + getName() + "," + getLicenseNo() + ","
               + getPhone() + "," + getVehicleType() + "," + getVehiclePlate() + ","
               + getStatus() + "," + getJoinedDate() + "," + hourlyRate + "," + hoursWorked;
    }
}

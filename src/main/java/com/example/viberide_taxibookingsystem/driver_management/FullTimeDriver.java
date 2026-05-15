package com.example.viberide_taxibookingsystem.driver_management;

import java.time.LocalDate;

public class FullTimeDriver extends Driver {

    private double monthlySalary;
    private String shiftHours;

    public FullTimeDriver(String driverId, String name, String licenseNo, String phone,
                          String vehicleType, String vehiclePlate, String status,
                          LocalDate joinedDate, double monthlySalary, String shiftHours) {
        super(driverId, name, licenseNo, phone, vehicleType, vehiclePlate, status, joinedDate);
        this.monthlySalary = monthlySalary;
        this.shiftHours = shiftHours;
    }

    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }
    public String getShiftHours() { return shiftHours; }
    public void setShiftHours(String shiftHours) { this.shiftHours = shiftHours; }

    @Override
    public double calculateEarnings() { return monthlySalary; }

    @Override
    public String getAssignmentDetails() {
        return "Full-Time: " + getName() + " | Shift: " + shiftHours
               + " | Salary: Rs." + String.format("%.2f", monthlySalary)
               + " | " + (isAvailable() ? "Available" : "Unavailable");
    }

    @Override
    public String toFileString() {
        return "FULLTIME," + getDriverId() + "," + getName() + "," + getLicenseNo() + ","
               + getPhone() + "," + getVehicleType() + "," + getVehiclePlate() + ","
               + getStatus() + "," + getJoinedDate() + "," + monthlySalary + "," + shiftHours;
    }
}

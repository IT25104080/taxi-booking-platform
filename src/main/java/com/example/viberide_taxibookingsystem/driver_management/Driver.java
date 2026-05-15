package com.example.viberide_taxibookingsystem.driver_management;

import java.time.LocalDate;

public abstract class Driver implements Assignable {

    private String driverId;
    private String name;
    private String licenseNo;
    private String phone;
    private String vehicleType;
    private String vehiclePlate;
    private String status;
    private LocalDate joinedDate;

    public Driver(String driverId, String name, String licenseNo, String phone,
                  String vehicleType, String vehiclePlate, String status,
                  LocalDate joinedDate) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNo = licenseNo;
        this.phone = phone;
        this.vehicleType = vehicleType;
        this.vehiclePlate = vehiclePlate;
        this.status = status;
        this.joinedDate = joinedDate;
    }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getJoinedDate() { return joinedDate; }
    public void setJoinedDate(LocalDate joinedDate) { this.joinedDate = joinedDate; }

    @Override
    public boolean isAvailable() {
        return "Available".equalsIgnoreCase(this.status);
    }

    public abstract String toFileString();
    public abstract double calculateEarnings();

    @Override
    public String toString() {
        return driverId + " | " + name + " | " + vehicleType + " | " + vehiclePlate + " | " + status;
    }
}

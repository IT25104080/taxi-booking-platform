package com.example.viberide_taxibookingsystem.driver_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DriverController {

    @GetMapping("/drivers")
    public String viewAllDrivers(Model model) throws IOException {
        List<Driver> driverList = DriverFileHandler.getAllDrivers();
        model.addAttribute("driverList", driverList);
        return "driver-list";
    }

    @GetMapping("/drivers/new")
    public String showDriverForm() { return "driver-form"; }

    @PostMapping("/drivers/fulltime")
    public String createFullTime(
            @RequestParam String driverId, @RequestParam String name,
            @RequestParam String licenseNo, @RequestParam String phone,
            @RequestParam String vehicleType, @RequestParam String vehiclePlate,
            @RequestParam String status, @RequestParam double monthlySalary,
            @RequestParam String shiftHours) throws IOException {
        FullTimeDriver ft = new FullTimeDriver(driverId, name, licenseNo, phone,
                vehicleType, vehiclePlate, status, LocalDate.now(), monthlySalary, shiftHours);
        DriverFileHandler.addDriver(ft);
        return "redirect:/drivers";
    }

    @PostMapping("/drivers/parttime")
    public String createPartTime(
            @RequestParam String driverId, @RequestParam String name,
            @RequestParam String licenseNo, @RequestParam String phone,
            @RequestParam String vehicleType, @RequestParam String vehiclePlate,
            @RequestParam String status, @RequestParam double hourlyRate,
            @RequestParam int hoursWorked) throws IOException {
        PartTimeDriver pt = new PartTimeDriver(driverId, name, licenseNo, phone,
                vehicleType, vehiclePlate, status, LocalDate.now(), hourlyRate, hoursWorked);
        DriverFileHandler.addDriver(pt);
        return "redirect:/drivers";
    }

    @GetMapping("/drivers/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Driver driver = DriverFileHandler.getDriverById(id);
        if (driver instanceof FullTimeDriver) {
            model.addAttribute("type", "FULLTIME");
            model.addAttribute("fullTimeDriver", (FullTimeDriver) driver);
        } else if (driver instanceof PartTimeDriver) {
            model.addAttribute("type", "PARTTIME");
            model.addAttribute("partTimeDriver", (PartTimeDriver) driver);
        } else {
            return "redirect:/drivers";
        }
        return "driver-edit";
    }

    @PostMapping("/drivers/update/fulltime")
    public String updateFullTime(
            @RequestParam String driverId, @RequestParam String name,
            @RequestParam String licenseNo, @RequestParam String phone,
            @RequestParam String vehicleType, @RequestParam String vehiclePlate,
            @RequestParam String status, @RequestParam String date,
            @RequestParam double monthlySalary, @RequestParam String shiftHours) throws IOException {
        FullTimeDriver ft = new FullTimeDriver(driverId, name, licenseNo, phone,
                vehicleType, vehiclePlate, status, LocalDate.parse(date), monthlySalary, shiftHours);
        DriverFileHandler.updateDriver(ft);
        return "redirect:/drivers";
    }

    @PostMapping("/drivers/update/parttime")
    public String updatePartTime(
            @RequestParam String driverId, @RequestParam String name,
            @RequestParam String licenseNo, @RequestParam String phone,
            @RequestParam String vehicleType, @RequestParam String vehiclePlate,
            @RequestParam String status, @RequestParam String date,
            @RequestParam double hourlyRate, @RequestParam int hoursWorked) throws IOException {
        PartTimeDriver pt = new PartTimeDriver(driverId, name, licenseNo, phone,
                vehicleType, vehiclePlate, status, LocalDate.parse(date), hourlyRate, hoursWorked);
        DriverFileHandler.updateDriver(pt);
        return "redirect:/drivers";
    }

    @GetMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable String id) throws IOException {
        DriverFileHandler.deleteDriver(id);
        return "redirect:/drivers";
    }
}

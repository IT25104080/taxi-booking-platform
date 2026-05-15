package com.example.viberide_taxibookingsystem.driver_management;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverFileHandler {

    private static final String FILE_PATH = "data/drivers.txt";

    public static void addDriver(Driver driver) throws IOException {
        new File("data").mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(driver.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Driver> getAllDrivers() throws IOException {
        List<Driver> driverList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return driverList;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 2) continue;
            String type = parts[0];

            if (type.equals("FULLTIME") && parts.length >= 11) {
                FullTimeDriver ft = new FullTimeDriver(
                    parts[1], parts[2], parts[3], parts[4], parts[5], parts[6],
                    parts[7], LocalDate.parse(parts[8]),
                    Double.parseDouble(parts[9]), parts[10]
                );
                driverList.add(ft);
            } else if (type.equals("PARTTIME") && parts.length >= 11) {
                PartTimeDriver pt = new PartTimeDriver(
                    parts[1], parts[2], parts[3], parts[4], parts[5], parts[6],
                    parts[7], LocalDate.parse(parts[8]),
                    Double.parseDouble(parts[9]), Integer.parseInt(parts[10])
                );
                driverList.add(pt);
            }
        }
        reader.close();
        return driverList;
    }

    public static Driver getDriverById(String driverId) throws IOException {
        for (Driver d : getAllDrivers()) {
            if (d.getDriverId().equals(driverId)) return d;
        }
        return null;
    }

    public static void updateDriver(Driver updatedDriver) throws IOException {
        List<Driver> all = getAllDrivers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Driver d : all) {
            writer.write(d.getDriverId().equals(updatedDriver.getDriverId())
                         ? updatedDriver.toFileString() : d.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    public static void deleteDriver(String driverId) throws IOException {
        List<Driver> all = getAllDrivers();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Driver d : all) {
            if (!d.getDriverId().equals(driverId)) {
                writer.write(d.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }
}

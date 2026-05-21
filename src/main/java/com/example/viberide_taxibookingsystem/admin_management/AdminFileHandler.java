package com.example.viberide_taxibookingsystem.admin_management;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminFileHandler {

    private static final String FILE_PATH = "data/admins.txt";

    public static void addAdmin(Admin admin) throws IOException {
        new File("data").mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(admin.toFileString());
        writer.newLine();
        writer.close();
    }

    public static List<Admin> getAllAdmins() throws IOException {
        List<Admin> adminList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return adminList;

        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 2) continue;
            String type = parts[0];

            if (type.equals("SUPER") && parts.length >= 9) {
                SuperAdmin sa = new SuperAdmin(
                    parts[1], parts[2], parts[3], parts[4], parts[5],
                    LocalDate.parse(parts[6]), parts[7], Integer.parseInt(parts[8])
                );
                adminList.add(sa);
            } else if (type.equals("SUPPORT") && parts.length >= 9) {
                SupportAdmin spa = new SupportAdmin(
                    parts[1], parts[2], parts[3], parts[4], parts[5],
                    LocalDate.parse(parts[6]), parts[7], Integer.parseInt(parts[8])
                );
                adminList.add(spa);
            }
        }
        reader.close();
        return adminList;
    }

    public static Admin getAdminById(String adminId) throws IOException {
        for (Admin a : getAllAdmins()) {
            if (a.getAdminId().equals(adminId)) return a;
        }
        return null;
    }

    public static void updateAdmin(Admin updatedAdmin) throws IOException {
        List<Admin> all = getAllAdmins();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Admin a : all) {
            writer.write(a.getAdminId().equals(updatedAdmin.getAdminId())
                         ? updatedAdmin.toFileString() : a.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    public static void deleteAdmin(String adminId) throws IOException {
        List<Admin> all = getAllAdmins();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Admin a : all) {
            if (!a.getAdminId().equals(adminId)) {
                writer.write(a.toFileString());
                writer.newLine();
            }
        }
        writer.close();
    }
}

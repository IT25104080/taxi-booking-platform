package com.taxi.taxibookingplatform.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // DATA_DIR = project folder + "/data/"
    private static final String DATA_DIR = System.getProperty("user.dir") + "/data/";

    // static block – runs once when the class is loaded
    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();   // create data folder if it doesn't exist
        }
    }

    // Read all lines from a file
    public static List<String> readAllLines(String fileName) throws IOException {
        File file = new File(DATA_DIR + fileName);
        if (!file.exists()) {
            file.createNewFile();   // create empty file if not exists
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    // Append one line to a file
    public static void appendLine(String fileName, String line) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_DIR + fileName, true))) {
            writer.write(line);
            writer.newLine();
        }
    }

    // Update a line that starts with a specific ID
    public static boolean updateLine(String fileName, String id, String newLine, int idIndex) throws IOException {
        List<String> lines = readAllLines(fileName);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\|");
            if (parts.length > idIndex && parts[idIndex].equals(id)) {
                lines.set(i, newLine);
                writeAllLines(fileName, lines);
                return true;
            }
        }
        return false;
    }

    // Write all lines (overwrite)
    private static void writeAllLines(String fileName, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_DIR + fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Delete a line by ID
    public static boolean deleteLine(String fileName, String id, int idIndex) throws IOException {
        List<String> lines = readAllLines(fileName);
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("\\|");
            if (parts.length > idIndex && parts[idIndex].equals(id)) {
                lines.remove(i);
                writeAllLines(fileName, lines);
                return true;
            }
        }
        return false;
    }
}
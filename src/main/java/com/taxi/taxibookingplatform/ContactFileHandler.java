package com.taxi.taxibookingplatform;

import java.io.*;
import java.time.LocalDateTime;

public class ContactFileHandler {

    private static final String FILE_PATH = "data/contacts.txt";

    public static void save(ContactMessage msg) throws IOException {
        new File("data").mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(msg.toFileString());
            writer.newLine();
        }
    }
}

package com.unevento.api.controllers.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDeletedService {

    private static final String UPLOAD_DIR = "Backend/src/main/resources/images";

    public static void deleteFile(String fileName) throws IOException {

        // Construct the file path using Paths.get()
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Verify if the file exists before deleting it
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        } else {
            throw new IOException("The file does not exist");
        }
    }
}



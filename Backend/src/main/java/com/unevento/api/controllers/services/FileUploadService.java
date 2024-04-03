package com.unevento.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "Backend/src/main/resources/images/";

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString(); // Unique ID for the file
        String fileNameOrginal = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Long fileSize = file.getSize();
        Long maxFileSize = 5 * 1024 * 1024L; // 5MB

        if (fileSize > maxFileSize) {
            throw new IOException("El archivo excede el tamaño permitido");
        } else if (fileSize == 0) {
            throw new IOException("El archivo no puede estar vacío");
        }
        if (!fileNameOrginal.endsWith(".jpg") && !fileNameOrginal.endsWith(".png") && !fileNameOrginal.endsWith(".jpeg")) {
            throw new IOException("El archivo no es una imagen");
        }
        String fileExtension = fileNameOrginal.substring(fileNameOrginal.lastIndexOf("."));
        String newFileName = fileName + fileExtension;

        // Save the file to the specified directory
        Path uploadedFilePath = Paths.get(UPLOAD_DIR + newFileName);
        Files.write(uploadedFilePath, bytes);

        // Return the path of the uploaded file
        return uploadedFilePath.toString();
    }
}

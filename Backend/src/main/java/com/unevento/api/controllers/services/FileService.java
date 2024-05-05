package com.unevento.api.controllers.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileService {

    private static final String bucketName = "uneventophoto.appspot.com";
    private static final String DOWNLOAD_URL_TEMPLATE = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
    private static final String pathToDownloadedJson = "Backend/src/main/resources/keys/uneventophoto-firebase-adminsdk-hmao9-efc957e87b.json";

    private static String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(pathToDownloadedJson));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return fileName;
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + getExtension(fileName);
            File file = convertToFile(multipartFile, fileName);
            String downloadUrl = uploadFile(file, fileName);
            file.delete();
            return downloadUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload file.");
        }
    }

    public void delete(String fileName) {
        try {
            // Crear una instancia de Storage
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // Construir el BlobId usando el nombre del archivo y el nombre del bucket
            BlobId blobId = BlobId.of(bucketName, fileName);

            // Eliminar el archivo de Firebase Storage
            if (storage.delete(blobId)) {
                System.out.println("Existe");
            } else {
                System.out.println("No existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete file.");
        }
    }


    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

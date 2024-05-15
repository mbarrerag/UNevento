package com.unevento.api.controllers.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileService {

    @Value("${private_key}")
    private String privateKey;

    @Value("${client_email}")
    private String clientEmail;

    private static final String bucketName = "uneventophoto.appspot.com";
    private static final String DOWNLOAD_URL_TEMPLATE = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";

    private Credentials createCredentials() throws IOException {
        File credentialsFile = new ClassPathResource("keys/uneventophoto-firebase-adminsdk-hmao9-efc957e87b.json").getFile();
        return GoogleCredentials.fromStream(new FileInputStream(credentialsFile));
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = createCredentials();
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
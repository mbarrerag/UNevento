package com.unevento.api.controllers.services;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

    @Value("${private_key}")
    private String privateKey;

    @Value("${client_email}")
    private String clientEmail;

    @Value("${project_id}")
    private String projectId;

    @Value("${private_key_id}")
    private String privateKeyId;

    @Value("${client_id}")
    private String clientId;

    @Value("${client_x509_cert_url}")
    private String clientX509CertUrl;

    @Value("${auth_provider_x509_cert_url}")
    private String authProviderX509CertUrl;

    @Value("${auth_uri}")
    private String authUri;

    @Value("${token_uri}")
    private String tokenUri;

    private static final String bucketName = "uneventophoto.appspot.com";
    private static final String DOWNLOAD_URL_TEMPLATE = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";

    private Credentials createCredentials() {
        try {
            privateKey = privateKey.replace("\\n", "\n").trim();

            Map<String, Object> map = new HashMap<>();
            map.put("type", "service_account");
            map.put("project_id", projectId);
            map.put("private_key_id", privateKeyId);
            map.put("private_key", privateKey);
            map.put("client_email", clientEmail);
            map.put("client_id", clientId);
            map.put("auth_uri", authUri);
            map.put("token_uri", tokenUri);
            map.put("auth_provider_x509_cert_url", authProviderX509CertUrl);
            map.put("client_x509_cert_url", clientX509CertUrl);

            return GoogleCredentials.fromStream(new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(map)))
                    .createScoped("https://www.googleapis.com/auth/cloud-platform");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Google credentials: " + e.getMessage());
        }
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
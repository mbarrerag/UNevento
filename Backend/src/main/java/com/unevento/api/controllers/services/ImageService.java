package com.unevento.api.controllers.services;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public String getImageName(String imagePath) {
        if (imagePath == null) {
            return "as.png"; // Default image name
        } else {
            int lastIndex = imagePath.lastIndexOf('/');
            if (lastIndex != -1) {
                return imagePath.substring(lastIndex + 1); // Extract the file name from the path
            } else {
                return imagePath; // If no path separator found, return the full path
            }
        }
    }
}

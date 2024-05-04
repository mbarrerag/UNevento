package com.unevento.api.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@RestController
@CrossOrigin
public class GetImage {

    @GetMapping("/images/{fileName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
        // Load image from URL
        BufferedImage originalImage = null;
        try {
            String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", "uneventophoto.appspot.com", fileName);

            originalImage = ImageIO.read(new URL(imageUrl));
            // Render the image (for example, resize)
            BufferedImage renderedImage = renderImage(originalImage);
            // Convert the rendered image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(renderedImage, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            // Return the rendered image
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new ByteArrayResource(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    private BufferedImage renderImage(BufferedImage originalImage) {
        // Perform any rendering operations here, for example, resizing
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage resizedImage = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width / 2, height / 2, null);
        g.dispose();
        return resizedImage;
    }
}

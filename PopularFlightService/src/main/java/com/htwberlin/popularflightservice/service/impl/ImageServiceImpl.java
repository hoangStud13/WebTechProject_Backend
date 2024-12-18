package com.htwberlin.popularflightservice.service.impl;

import com.htwberlin.popularflightservice.entities.Image;
import com.htwberlin.popularflightservice.exception.ResourceNotFoundException;
import com.htwberlin.popularflightservice.repository.ImageRepository;
import com.htwberlin.popularflightservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void createImageService(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image readImageService(Long imageServiceId) {
        return imageRepository.findById(imageServiceId).orElseThrow(
                () -> new ResourceNotFoundException("ImageService", "imageServiceId", "" + imageServiceId)
        );
    }

    @Override
    public boolean updateImageService(Image image) {
        if (image != null ) {
            // Überprüfung, ob das Bild existiert
            imageRepository.findById(image.getImageServiceId()).orElseThrow(
                    () -> new ResourceNotFoundException("ImageService", "imageServiceId", "" + image.getImageServiceId())
            );
            // Bild aktualisieren
            imageRepository.save(image);
            return true;
        } else {
            throw new IllegalArgumentException("Image must not be null and imageServiceId must not be null");
        }
    }

    @Override
    public boolean deleteImageService(Long imageServiceId) {
        Image image = imageRepository.findById(imageServiceId).orElseThrow(
                () -> new ResourceNotFoundException("ImageService", "imageServiceId", "" + imageServiceId)
        );
        imageRepository.delete(image);
        return true;
    }

    @Override
    public List<Image> readAllImageServices() {
        return imageRepository.findAll();
    }
}

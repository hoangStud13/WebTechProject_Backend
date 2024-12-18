package com.htwberlin.popularflightservice.service;


import com.htwberlin.popularflightservice.entities.Image;

import java.util.List;

public interface ImageService {

    void createImageService(Image image);

    Image readImageService(Long imageServiceId);

    boolean updateImageService(Image image);

    boolean deleteImageService(Long imageServiceId);

    List<Image> readAllImageServices();
}




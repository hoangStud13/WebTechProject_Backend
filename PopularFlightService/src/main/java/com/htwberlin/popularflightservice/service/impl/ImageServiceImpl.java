package com.htwberlin.popularflightservice.service.impl;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.entities.Image;
import com.htwberlin.popularflightservice.entities.PopularFlightService;
import com.htwberlin.popularflightservice.mapper.PopularFlightServiceMapper;
import com.htwberlin.popularflightservice.repository.ImageRepository;
import com.htwberlin.popularflightservice.repository.PopularFlightServiceRepository;
import com.htwberlin.popularflightservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {

    ImageRepository imageRepository;
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void createImageService(Image image) {
        ImageService imageService = ImageServiceMapper.mapToImageService(popularFlightServiceDto,new PopularFlightService());
        imageRepository.save(image);
    }

}

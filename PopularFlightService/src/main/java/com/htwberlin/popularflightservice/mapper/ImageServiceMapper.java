package com.htwberlin.popularflightservice.mapper;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.entities.Image;
import com.htwberlin.popularflightservice.entities.PopularFlightService;
import com.htwberlin.popularflightservice.service.ImageService;

public class ImageServiceMapper {

    public static PopularFlightService mapToPopularFlightService(Image image, ImageService imageService) {
        imageService.setImageServiceId(Image.getImageServiceId()); // remove?
        imageService.setPopularFlightServiceId(popularFlightServiceDto.getPopularFlightServiceId());
        imageService.setCountry(popularFlightServiceDto.getCountry());
        imageService.setCity(popularFlightServiceDto.getCity());
        imageService.setDeparture(popularFlightServiceDto.getDeparture());
        imageService.setDestination(popularFlightServiceDto.getDestination());


        return imageService;
    }


}

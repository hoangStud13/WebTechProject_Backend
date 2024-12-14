package com.htwberlin.popularflightservice.mapper;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.entities.PopularFlightService;

public class PopularFlightServiceMapper {

    public static PopularFlightServiceDto mapToPopularFlightServiceDto(PopularFlightService popularFlightService, PopularFlightServiceDto popularFlightServiceDto) {
        popularFlightServiceDto.setIataCode(popularFlightService.getIataCode());
        popularFlightServiceDto.setPopularFlightServiceId(popularFlightService.getPopularFlightServiceId());
        popularFlightServiceDto.setCountry(popularFlightService.getCountry());
        popularFlightServiceDto.setCity(popularFlightService.getCity());
        popularFlightServiceDto.setDeparture(popularFlightService.getDeparture());
        popularFlightServiceDto.setDestination(popularFlightService.getDestination());

        return popularFlightServiceDto;
    }

    public static PopularFlightService mapToPopularFlightService(PopularFlightServiceDto popularFlightServiceDto, PopularFlightService popularFlightService) {
        popularFlightService.setIataCode(popularFlightServiceDto.getIataCode()); // remove?
        popularFlightService.setPopularFlightServiceId(popularFlightServiceDto.getPopularFlightServiceId());
        popularFlightService.setCountry(popularFlightServiceDto.getCountry());
        popularFlightService.setCity(popularFlightServiceDto.getCity());
        popularFlightService.setDeparture(popularFlightServiceDto.getDeparture());
        popularFlightService.setDestination(popularFlightServiceDto.getDestination());


        return popularFlightService;
    }

}

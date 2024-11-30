package com.htwberlin.popularflightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor

public class PopularFlightServiceDto {

    private String iataCode;
    private Long popularFlightServiceId;
    private String country;
    private String city;
    private String departure;
    private String destination;
}

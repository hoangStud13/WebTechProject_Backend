package com.htwberlin.popularflightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name ="TicketDto",
        description = "Holds information about the ticket"
)
public class PopularFlightServiceDto {

    private Long iataCode;
    private Long popularFlightServiceId;
    private String country;
    private String city;
    private String departure;
    private String destination;
}

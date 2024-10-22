package de.htwberlin.armanairlines.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class TicketDto {
    private Long flightId;


    private String name;


    private String departure;


    private String destination;


    private String gate;


    private LocalDate date;


    private String seat;


    private LocalDateTime boardingTime;

}

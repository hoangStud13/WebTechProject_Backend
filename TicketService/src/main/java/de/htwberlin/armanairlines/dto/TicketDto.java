package de.htwberlin.armanairlines.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name ="TicketDto",
        description = "Holds information about the ticket"
)
public class TicketDto {
    private Long flightId;
    private Long userId;

    private String name;


    private String departure;


    private String destination;


    private String gate;


    private LocalDate date;


    private String seat;


    private LocalDateTime boardingTime;

}

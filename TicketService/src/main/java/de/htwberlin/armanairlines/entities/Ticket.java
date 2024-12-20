package de.htwberlin.armanairlines.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
@Data @AllArgsConstructor @NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_Id")
    private Long flightId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;

    @Column(name = "gate")
    private String gate;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "seat")
    private String seat;

    @Column(name = "boarding_Time")
    private LocalDateTime boardingTime;



}

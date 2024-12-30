package de.htwberlin.usermanagementservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticket_id;

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

    // Nur user_id statt User-Referenz
    @Column(name = "user_id", nullable = false)
    private Integer userId; // Der Fremdschlüssel für den User
}

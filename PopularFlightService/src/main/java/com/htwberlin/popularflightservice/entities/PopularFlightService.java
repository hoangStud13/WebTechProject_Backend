package com.htwberlin.popularflightservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "PopularFlightService")
@Data @AllArgsConstructor @NoArgsConstructor

public class PopularFlightService {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)


@Column(name= "iataCode")
private Long iataCode;

@Column (name = "popularFlightService_Id")
private Long popularFlightServiceId;

@Column(name = "country")
private String country;

@Column(name = "city")
private String city;

@Column(name = "departure")
private String departure;

@Column(name = "destination")
private String destination;
}

package com.htwberlin.popularflightservice.repository;

import com.htwberlin.popularflightservice.entities.PopularFlightService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopularFlightServiceRepository extends JpaRepository<PopularFlightService, Long> {


}

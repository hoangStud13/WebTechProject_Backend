package de.htwberlin.flightsservice.repository;

import de.htwberlin.flightsservice.entities.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportsRepository extends JpaRepository<Airports,String> {

    List<Airports> findByMunicipalityLike(String municipality);
}

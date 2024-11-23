package de.htwberlin.flightsservice.repository;

import de.htwberlin.flightsservice.entities.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportsRepository extends JpaRepository<Airports,String> {

    @Query("SELECT a FROM Airports a WHERE a.municipality LIKE %?1% ORDER BY CASE WHEN a.type = 'large_airport' THEN 1 WHEN a.type = 'medium_airport' THEN 2 ELSE 3 END")
    List<Airports> findByMunicipalityLike(String municipality);
}

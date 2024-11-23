package de.htwberlin.flightsservice.repository;

import de.htwberlin.flightsservice.entities.IsoCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsoCountryRepository extends JpaRepository<IsoCountry,String> {
}

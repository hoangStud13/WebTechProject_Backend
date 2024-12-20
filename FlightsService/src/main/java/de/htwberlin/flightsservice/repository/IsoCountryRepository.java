package de.htwberlin.flightsservice.repository;

import de.htwberlin.flightsservice.entities.IsoCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsoCountryRepository extends JpaRepository<IsoCountry,String> {
}

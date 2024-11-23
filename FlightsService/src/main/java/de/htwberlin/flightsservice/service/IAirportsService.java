package de.htwberlin.flightsservice.service;

import de.htwberlin.flightsservice.dto.AirportsDto;
import de.htwberlin.flightsservice.entities.Airports;

import java.util.List;

public interface IAirportsService {


    /**
     * Given an Iata Code, the method returns the Airport with the given IataCode
     * @param iataCode a 3 three letter String Code
     * @return Airport with IataCode
     */
    AirportsDto readAirportsById(String iataCode);


    /**
     * Given an municipality, the method returns the Airport with the given municipality
     * @param municipality such as Berlin e.g
     * @return Airport with municipality
     */
    List<AirportsDto> readAirportsByMunicipality(String municipality);


    /**
     * Gives all Airports back
     */
    List<AirportsDto> readAllAirports();

    /**
     * Takes an String, and looks up the Iso Country Code and maps it with a country
     * For Example de -> germany, en -> england
     * @param countryCode the iso abbreviation of the country
     * @return the name of the country
     */
    String mapCountry(String countryCode);
}

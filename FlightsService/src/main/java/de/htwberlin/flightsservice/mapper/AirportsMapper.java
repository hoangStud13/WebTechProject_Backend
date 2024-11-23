package de.htwberlin.flightsservice.mapper;

import de.htwberlin.flightsservice.dto.AirportsDto;
import de.htwberlin.flightsservice.entities.Airports;

public class AirportsMapper {
    public static AirportsDto mapToFlightsDto(Airports airports, AirportsDto airportsDto) {

        airportsDto.setIdent(airports.getIdent());
        airportsDto.setType(airports.getType());
        airportsDto.setName(airports.getName());
        airportsDto.setContinent(airports.getContinent());
        airportsDto.setIsoCountry(airports.getIsoCountry());
        airportsDto.setMunicipality(airports.getMunicipality());
        airportsDto.setIataCode(airports.getIataCode());
        airportsDto.setCoordinate(airports.getCoordinate());

        return airportsDto;
    }

    public static Airports mapToFlights(Airports airports, AirportsDto airportsDto) {
        airports.setIdent(airportsDto.getIdent());
        airports.setType(airportsDto.getType());
        airports.setName(airportsDto.getName());
        airports.setContinent(airportsDto.getContinent());
        airports.setIsoCountry(airportsDto.getIsoCountry());
        airports.setMunicipality(airportsDto.getMunicipality());
        airports.setIataCode(airportsDto.getIataCode());
        airports.setCoordinate(airportsDto.getCoordinate());
        return airports;
    }
}

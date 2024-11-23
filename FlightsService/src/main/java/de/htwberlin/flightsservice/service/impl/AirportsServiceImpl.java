package de.htwberlin.flightsservice.service.impl;

import de.htwberlin.flightsservice.dto.AirportsDto;
import de.htwberlin.flightsservice.entities.Airports;
import de.htwberlin.flightsservice.exception.ResourceNotFoundException;
import de.htwberlin.flightsservice.mapper.AirportsMapper;
import de.htwberlin.flightsservice.repository.AirportsRepository;
import de.htwberlin.flightsservice.repository.IsoCountryRepository;
import de.htwberlin.flightsservice.service.IAirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportsServiceImpl implements IAirportsService {

    AirportsRepository airportsRepository;
    IsoCountryRepository isoCountryRepository;

    @Autowired
    public AirportsServiceImpl(AirportsRepository airportsRepository, IsoCountryRepository isoCountryRepository) {
        this.airportsRepository = airportsRepository;
        this.isoCountryRepository = isoCountryRepository;
    }

    @Override
    public AirportsDto readAirportsById(String iataCode) {
        Airports airports = airportsRepository.findById(iataCode).orElseThrow(
                () -> new ResourceNotFoundException("Airport", "iataCode", "" + iataCode)
        );

        AirportsDto airportsDto = AirportsMapper.mapToFlightsDto(airports,new AirportsDto());
        airportsDto.setCountry(mapCountry(airportsDto.getIsoCountry()));
        return airportsDto;
    }


    @Override
    public List<AirportsDto> readAirportsByMunicipality(String municipality) {
        List<Airports> airports = airportsRepository.findByMunicipalityLike(municipality);

        List<AirportsDto> airportsDtos = new ArrayList<>();

        for (Airports airportsTemp : airports
             ) {
            AirportsDto airportsTempDto = AirportsMapper.mapToFlightsDto(airportsTemp, new AirportsDto());
            airportsTempDto.setCountry(airportsTempDto.getIsoCountry());
            airportsDtos.add(airportsTempDto);
        }

        return airportsDtos;
    }

    @Override
    public List<AirportsDto> readAllAirports() {

        List<Airports> airports = airportsRepository.findAll();
        List<AirportsDto> airportsDtos = new ArrayList<>();
        for (Airports airportsTemp : airports
        ) {
            AirportsDto airportsTempDto = AirportsMapper.mapToFlightsDto(airportsTemp, new AirportsDto());
            airportsTempDto.setCountry(airportsTempDto.getIsoCountry());
            airportsDtos.add(airportsTempDto);
        }
        return airportsDtos;
    }

    @Override
    public String mapCountry(String countryCode) {
        String country = String.valueOf(isoCountryRepository.findById(countryCode).orElseThrow(
                () -> new ResourceNotFoundException("Country", "countryCode", "" + countryCode)
        ));
        return country;
    }
}

package de.htwberlin.flightsservice.controller;

import de.htwberlin.flightsservice.dto.AirportsDto;
import de.htwberlin.flightsservice.service.IAirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/AirportRestAPI")
public class AirportsController {


    private IAirportsService iAirportsService;

    @Autowired
    public AirportsController(IAirportsService iAirportsService) {
        this.iAirportsService = iAirportsService;
    }

    @GetMapping("/municipality/{city}")
    public ResponseEntity<List<AirportsDto>> findMunicipality(@PathVariable String city){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(iAirportsService.readAirportsByMunicipality(city));

    }



}

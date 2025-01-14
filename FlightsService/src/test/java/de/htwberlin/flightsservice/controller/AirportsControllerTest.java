package de.htwberlin.flightsservice.controller;

import de.htwberlin.flightsservice.dto.AirportsDto;
import de.htwberlin.flightsservice.service.IAirportsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AirportsControllerTest {

    @Mock
    private IAirportsService iAirportsService;

    private AirportsController airportsController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airportsController = new AirportsController(iAirportsService);
        mockMvc = MockMvcBuilders.standaloneSetup(airportsController).build();
    }

    @Test
    @DisplayName("Find Airports by Municipality - Success")
    void findAirportsByMunicipalitySuccess() throws Exception {
        String city = "Berlin";

        AirportsDto airport1 = new AirportsDto();
        airport1.setIdent("EDDB");
        airport1.setName("Berlin Brandenburg Airport");

        AirportsDto airport2 = new AirportsDto();
        airport2.setIdent("EDDM");
        airport2.setName("Munich Airport");

        List<AirportsDto> airports = Arrays.asList(airport1, airport2);

        when(iAirportsService.readAirportsByMunicipality(city)).thenReturn(airports);

        mockMvc.perform(get("/api/AirportRestAPI/municipality/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ident").value("EDDB"))
                .andExpect(jsonPath("$[1].ident").value("EDDM"))
                .andExpect(jsonPath("$[0].name").value("Berlin Brandenburg Airport"))
                .andExpect(jsonPath("$[1].name").value("Munich Airport"));
    }

    @Test
    @DisplayName("Find Airports by Municipality - No Airports Found")
    void findAirportsByMunicipalityNoAirports() throws Exception {
        String city = "NonExistentCity";

        when(iAirportsService.readAirportsByMunicipality(city)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/AirportRestAPI/municipality/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }



}

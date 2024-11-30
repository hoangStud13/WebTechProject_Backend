package com.htwberlin.popularflightservice.service;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import java.util.List;

public interface IPopularFlightService {

void createPopularFlightService (PopularFlightServiceDto popularFlightServiceDto);

PopularFlightServiceDto readPopularFlightService (Long popularFlightId);

boolean updatePopularFlightService (PopularFlightServiceDto popularFlightServiceDto);

boolean deletePopularFlightService (Long popularFlightId);

List <PopularFlightServiceDto> readAllPopularFlightService ();
}

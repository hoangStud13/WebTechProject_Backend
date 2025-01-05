package com.htwberlin.popularflightservice.service;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;

import java.util.List;

/**
 * Interface for the service that manages popular flight services.
 * This interface defines the basic CRUD operations (Create, Read, Update, Delete)
 * for popular flight services.
 */
public interface IPopularFlightService {

    /**
     * Creates a new popular flight service.
     *
     * @param popularFlightServiceDto The DTO containing the data for the flight service to be created.
     */
    void createPopularFlightService(PopularFlightServiceDto popularFlightServiceDto);

    /**
     * Reads a popular flight service based on the given ID.
     *
     * @param popularFlightServiceId The ID of the popular flight service to read.
     * @return The DTO of the found popular flight service, or null if no flight service was found.
     */
    PopularFlightServiceDto readPopularFlightService(Long popularFlightServiceId);

    /**
     * Updates an existing popular flight service.
     *
     * @param popularFlightServiceDto The DTO with the updated data of the flight service.
     * @return true if the update was successful, false otherwise.
     */
    boolean updatePopularFlightService(PopularFlightServiceDto popularFlightServiceDto);

    /**
     * Deletes a popular flight service based on the given ID.
     *
     * @param popularFlightServiceId The ID of the popular flight service to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deletePopularFlightService(Long popularFlightServiceId);

    /**
     * Reads all available popular flight services.
     *
     * @return A list of DTOs representing all the popular flight services.
     */
    List<PopularFlightServiceDto> readAllPopularFlightServices();
}

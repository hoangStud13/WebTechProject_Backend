package com.htwberlin.popularflightservice.service.impl;

import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.entities.PopularFlightService;
import com.htwberlin.popularflightservice.exception.ResourceNotFoundException;
import com.htwberlin.popularflightservice.mapper.PopularFlightServiceMapper;
import com.htwberlin.popularflightservice.repository.PopularFlightServiceRepository;
import com.htwberlin.popularflightservice.service.IPopularFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IPopularFlightService interface for CRUD operations
 * related to PopularFlightService.
 */
@Service
public class PopularFlightServiceImpl implements IPopularFlightService {

    private final PopularFlightServiceRepository popularFlightServiceRepository;

    /**
     * Constructor to initialize the PopularFlightServiceRepository.
     *
     * @param popularFlightServiceRepository the repository for PopularFlightService.
     */
    @Autowired
    public PopularFlightServiceImpl(PopularFlightServiceRepository popularFlightServiceRepository) {
        this.popularFlightServiceRepository = popularFlightServiceRepository;
    }

    /**
     * Creates a new PopularFlightService entry.
     *
     * @param popularFlightServiceDto the data of the PopularFlightService to be created.
     */
    @Override
    public void createPopularFlightService(PopularFlightServiceDto popularFlightServiceDto) {
        PopularFlightService popularFlightService = PopularFlightServiceMapper.mapToPopularFlightService(popularFlightServiceDto, new PopularFlightService());
        popularFlightServiceRepository.save(popularFlightService);
    }

    /**
     * Retrieves a PopularFlightService based on its ID.
     *
     * @param popularFlightServiceId the ID of the PopularFlightService to be retrieved.
     * @return the DTO of the found PopularFlightService.
     * @throws ResourceNotFoundException if no PopularFlightService exists with the given ID.
     */
    @Override
    public PopularFlightServiceDto readPopularFlightService(Long popularFlightServiceId) {
        PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("PopularFlight", "popularFlightId", "" + popularFlightServiceId));

        return PopularFlightServiceMapper.mapToPopularFlightServiceDto(popularFlightService, new PopularFlightServiceDto());
    }

    /**
     * Updates an existing PopularFlightService.
     *
     * @param popularFlightServiceDto the updated data of the PopularFlightService.
     * @return true if the update was successful; false otherwise.
     * @throws IllegalArgumentException if the DTO or the ID is null.
     * @throws ResourceNotFoundException if no PopularFlightService exists with the given ID.
     */
    @Override
    public boolean updatePopularFlightService(PopularFlightServiceDto popularFlightServiceDto) {
        boolean isUpdated = false;

        if (popularFlightServiceDto != null && popularFlightServiceDto.getPopularFlightServiceId() != null) {
            PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceDto.getPopularFlightServiceId())
                    .orElseThrow(() -> new ResourceNotFoundException("popularFlightService", "popularFlightServiceId", "" + popularFlightServiceDto.getPopularFlightServiceId()));

            popularFlightService = PopularFlightServiceMapper.mapToPopularFlightService(popularFlightServiceDto, popularFlightService);
            popularFlightServiceRepository.save(popularFlightService);
            isUpdated = true;
        } else {
            throw new IllegalArgumentException("PopularFlightServiceDto or popularFlightServiceId must not be null");
        }

        return isUpdated;
    }

    /**
     * Deletes a PopularFlightService based on its ID.
     *
     * @param popularFlightServiceId the ID of the PopularFlightService to be deleted.
     * @return true if the deletion was successful.
     * @throws ResourceNotFoundException if no PopularFlightService exists with the given ID.
     */
    @Override
    public boolean deletePopularFlightService(Long popularFlightServiceId) {
        PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("popularFlightService", "popularFlightServiceId", "" + popularFlightServiceId));

        popularFlightServiceRepository.delete(popularFlightService);
        return true;
    }

    /**
     * Retrieves all PopularFlightServices.
     *
     * @return a list of PopularFlightServiceDto objects representing all the PopularFlightServices.
     */
    public List<PopularFlightServiceDto> readAllPopularFlightServices() {
        List<PopularFlightService> popularFlightServices = popularFlightServiceRepository.findAll();
        List<PopularFlightServiceDto> popularFlightServicesDtos = new ArrayList<>();
        for (PopularFlightService popularFlightService : popularFlightServices) {
            popularFlightServicesDtos.add(PopularFlightServiceMapper.mapToPopularFlightServiceDto(popularFlightService, new PopularFlightServiceDto()));
        }
        return popularFlightServicesDtos;
    }
}

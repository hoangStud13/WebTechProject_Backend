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


@Service
public class PopularFlightServiceImpl implements IPopularFlightService {


    PopularFlightServiceRepository popularFlightServiceRepository;
    @Autowired
    public PopularFlightServiceImpl(PopularFlightServiceRepository popularFlightServiceRepository) {
        this.popularFlightServiceRepository = popularFlightServiceRepository;
    }

    @Override
    public void createPopularFlightService(PopularFlightServiceDto popularFlightServiceDto) {
        PopularFlightService popularFlightService = PopularFlightServiceMapper.mapToPopularFlightService(popularFlightServiceDto,new PopularFlightService());
        popularFlightServiceRepository.save(popularFlightService);
    }

    @Override
    public PopularFlightServiceDto readPopularFlightService(Long popularFlightServiceId) {
        PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceId).orElseThrow(
                ()->new ResourceNotFoundException("PopularFlight", "popularFlightId",""+popularFlightServiceId)
        );

        PopularFlightServiceDto popularFlightServiceDto = PopularFlightServiceMapper.mapToPopularFlightServiceDto(popularFlightService,new PopularFlightServiceDto());
        return popularFlightServiceDto;
    }

    @Override
    public boolean updatePopularFlightService(PopularFlightServiceDto popularFlightServiceDto) {
        boolean isUpdated = false;

        if(popularFlightServiceDto != null && popularFlightServiceDto.getPopularFlightServiceId() != null) {
            // Prüfen, ob PopularFlightService existiert, andernfalls Exception werfen
            PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceDto.getPopularFlightServiceId()).orElseThrow(
                    () -> new ResourceNotFoundException("popularFlightService", "popularFlightServiceId", "" + popularFlightServiceDto.getPopularFlightServiceId())
            );

            // Mapping vom PopularFlightServiceDto auf PopularFlightService
            popularFlightService = PopularFlightServiceMapper.mapToPopularFlightService(popularFlightServiceDto, popularFlightService);

            // PopularFlightService in der Datenbank aktualisieren
            popularFlightServiceRepository.save(popularFlightService);
            isUpdated = true;
        } else {
            throw new IllegalArgumentException("PopularFlightServiceDto or popularFlightServiceId must not be null");
        }

        return  isUpdated;
    }


    @Override
    public boolean deletePopularFlightService(Long popularFlightServiceId) {
        PopularFlightService popularFlightService = popularFlightServiceRepository.findById(popularFlightServiceId).orElseThrow(
                ()->new ResourceNotFoundException("popularFlightService", "popularFlightServiceId",""+popularFlightServiceId)
        );

        popularFlightServiceRepository.delete(popularFlightService);
        return true;
    }

    public List<PopularFlightServiceDto> readAllPopularFlightServices() {
        List<PopularFlightService> popularFlightServices = popularFlightServiceRepository.findAll();
        List<PopularFlightServiceDto> popularFlightServicesDtos = new ArrayList<>();
        for (PopularFlightService popularFlightService : popularFlightServices) {
            popularFlightServicesDtos.add(PopularFlightServiceMapper.mapToPopularFlightServiceDto(popularFlightService, new PopularFlightServiceDto()));
        }
        return popularFlightServicesDtos;
    }

}

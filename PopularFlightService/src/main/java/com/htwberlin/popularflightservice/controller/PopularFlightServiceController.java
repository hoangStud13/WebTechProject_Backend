package com.htwberlin.popularflightservice.controller;

import com.htwberlin.popularflightservice.constants.PopularFlightServiceConstants;
import com.htwberlin.popularflightservice.dto.ErrorResponseDto;
import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.dto.ResponseDto;
import com.htwberlin.popularflightservice.entities.PopularFlightService;
import com.htwberlin.popularflightservice.service.IPopularFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for CRUD operations related to PopularFlightService.
 */
@RestController
@RequestMapping("/popularFlightServiceAPI")
@Tag(
        name = "CRUD PopularFlightService Rest API",
        description = "CRUD REST APIs for our web application to CREATE, UPDATE, GET, and DELETE PopularFlightService data."
)
public class PopularFlightServiceController {

    private IPopularFlightService popularFlightService;

    /**
     * Constructor for injecting the implementation of PopularFlightService.
     *
     * @param popularFlightService the implementation of PopularFlightService.
     */
    @Autowired
    public PopularFlightServiceController(IPopularFlightService popularFlightService) {
        this.popularFlightService = popularFlightService;
    }

    /**
     * Retrieves a PopularFlightService based on its ID.
     *
     * @param popularFlightServiceId the ID of the PopularFlightService.
     * @return ResponseEntity containing the PopularFlightServiceDto.
     */
    @Operation(
            summary = "Retrieve PopularFlightService REST API",
            description = "REST API to retrieve a PopularFlightService based on its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/popularFlightService/{popularFlightServiceId}")
    public ResponseEntity<PopularFlightServiceDto> getPopularFlightService(@PathVariable Long popularFlightServiceId) {
        PopularFlightServiceDto popularFlightServiceDto = popularFlightService.readPopularFlightService(popularFlightServiceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popularFlightServiceDto);
    }

    /**
     * Creates a new PopularFlightService.
     *
     * @param popularFlightServiceDto the data for the new PopularFlightService.
     * @return ResponseEntity with a success message and status.
     */
    @Operation(
            summary = "Create PopularFlightService REST API",
            description = "REST API to create a new PopularFlightService."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/popularFlightService")
    public ResponseEntity<ResponseDto> addPopularFlightService(@RequestBody PopularFlightServiceDto popularFlightServiceDto) {
        popularFlightService.createPopularFlightService(popularFlightServiceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_201, PopularFlightServiceConstants.STATUS_201));
    }

    /**
     * Updates an existing PopularFlightService.
     *
     * @param popularFlightServiceDto the updated data for the PopularFlightService.
     * @return ResponseEntity with a success or error message and status.
     */
    @Operation(
            summary = "Update PopularFlightService REST API",
            description = "REST API to update an existing PopularFlightService."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status UPDATED",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP status FAILED",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/popularFlightService")
    public ResponseEntity<ResponseDto> updatePopularFlightService(@RequestBody PopularFlightServiceDto popularFlightServiceDto) {
        boolean isUpdated = popularFlightService.updatePopularFlightService(popularFlightServiceDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_200, PopularFlightServiceConstants.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_417_UPDATE, PopularFlightServiceConstants.STATUS_417));
        }
    }

    /**
     * Deletes a PopularFlightService based on its ID.
     *
     * @param popularFlightServiceId the ID of the PopularFlightService to be deleted.
     * @return ResponseEntity with a success or error message and status.
     */
    @Operation(
            summary = "Delete PopularFlightService REST API",
            description = "REST API to delete a PopularFlightService based on its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP status FAILED",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/popularFlightService/{popularFlightServiceId}")
    public ResponseEntity<ResponseDto> deletePopularFlightService(@PathVariable Long popularFlightServiceId) {
        boolean isDeleted = popularFlightService.deletePopularFlightService(popularFlightServiceId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_200, PopularFlightServiceConstants.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_417_DELETE, PopularFlightServiceConstants.STATUS_417));
        }
    }

    /**
     * Retrieves all PopularFlightServices.
     *
     * @return ResponseEntity with a list of PopularFlightServiceDto.
     */
    @Operation(
            summary = "Retrieve all PopularFlightServices REST API",
            description = "REST API to retrieve all PopularFlightServices."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/popularFlightService")
    public ResponseEntity<List<PopularFlightServiceDto>> getAllPopularFlightService() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popularFlightService.readAllPopularFlightServices());
    }
}

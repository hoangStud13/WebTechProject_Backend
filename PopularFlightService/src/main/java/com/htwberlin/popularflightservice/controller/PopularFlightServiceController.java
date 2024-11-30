package com.htwberlin.popularflightservice.controller;

import com.htwberlin.popularflightservice.constants.PopularFlightServiceConstants;
import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.dto.ResponseDto;
import com.htwberlin.popularflightservice.entities.PopularFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/popularFlightServiceAPI")
/*@Tag(
        name = "CRUD PopularFlightService Rest API",
        description = "CRUD REST API's for our Webapplication to CREATE, UPDATE, FETCH AND DELETE PopularFlightService Details"
)*/

public class PopularFlightServiceController {


    private PopularFlightService popularFlightService;
    @Autowired
    public PopularFlightServiceController(PopularFlightService popularFlightService) {this.popularFlightService = popularFlightService;}


    @Operation(
            summary = "Fetch PopularFlightService REST API",
            description = "REST API to fetch a PopularFlightService based on PopularFlightService ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )

    public ResponseEntity<PopularFlightServiceDto>getPopularFlightService(@PathVariable Long popularFlightServiceId){

        PopularFlightServiceDto popularFlightServiceDto = popularFlightService.readPopularFlightService(popularFlightServiceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popularFlightServiceDto);
    }

    @Operation(
            summary = "Create PopularFlightService REST API",
            description = "REST API to create a new PopularFlightService"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED",
            content = @Content(mediaType = "application/json")
    )

    @PostMapping("/popularFlightService")
    public ResponseEntity<ResponseDto>addPopularFlightService(@RequestBody PopularFlightServiceDto popularFlightServiceDto){
        popularFlightService.createPopularFlightService(popularFlightServiceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_201,PopularFlightServiceConstants.STATUS_201));
    }


    @Operation(
            summary = "Update PopularFlightsService REST API",
            description = "REST API to update an existing Ticket"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status UPDATED",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status FAILED",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class) //Swawgger gar nicht genutzt, kann man das weglassen?
                    )
            )
    })

    @PutMapping("/popularFlightService")
    public ResponseEntity<ResponseDto>updatePopularFlightService(@RequestBody PopularFlightServiceDto popularFlightServiceDto){


        boolean isUpdated = popularFlightService.updatePopularFlightService(popularFlightServiceDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_200, PopularFlightServiceConstants.STATUS_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_417_UPDATE,PopularFlightServiceConstants.STATUS_417));
        }
    }


    @Operation(
            summary = "Delete PopularFlightService REST API",
            description = "REST API to delete a Ticket by Flight ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status FAILED",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @DeleteMapping("/popularFlightService/{popularFlightServiceId}")
    public ResponseEntity<ResponseDto>deletePopularFlightService(@PathVariable Long popularFlightServiceId){

        boolean isDeleted = popularFlightService.deletePopularFlightService(popularFlightServiceId);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_200,PopularFlightServiceConstants.STATUS_200));
        }else{
            return  ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PopularFlightServiceConstants.MESSAGE_417_DELETE,PopularFlightServiceConstants.STATUS_417));
        }

    }

    @Operation(
            summary = "Get all Tickets REST API",
            description = "REST API to get all Tickets"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status successful",
            content = @Content(mediaType = "application/json")
    )

    @GetMapping("/popularFlightService")
    public ResponseEntity<PopularFlightServiceDto>getAllPopularFlightService(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(popularFlightService.readAllPopularFlightService());
    }
    }





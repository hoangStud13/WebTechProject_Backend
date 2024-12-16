package com.htwberlin.popularflightservice.controller;

import com.htwberlin.popularflightservice.constants.ImageServiceConstants;
import com.htwberlin.popularflightservice.constants.PopularFlightServiceConstants;
import com.htwberlin.popularflightservice.dto.ErrorResponseDto;
import com.htwberlin.popularflightservice.dto.PopularFlightServiceDto;
import com.htwberlin.popularflightservice.dto.ResponseDto;
import com.htwberlin.popularflightservice.entities.Image;
import com.htwberlin.popularflightservice.repository.ImageRepository;
import com.htwberlin.popularflightservice.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/imageServiceAPI")
@Tag(
        name = "CRUD imageService Rest API",
        description = "CRUD REST API's for our Webapplication to CREATE, UPDATE, FETCH AND DELETE ImageService Details"
)

public class ImageServiceController {

    private ImageService imageService;

    @Autowired
    public ImageServiceController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(
            summary = "Fetch ImageService REST API",
            description = "REST API to fetch a ImageService based on ImageService ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )

    @GetMapping("/imageService/{imageServiceId}")
    public ResponseEntity<Image> getImageService(@PathVariable Long imageServiceId) {

        Image image = imageService.readImageService(imageServiceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(image);
    }

    @Operation(
            summary = "Create ImageService REST API",
            description = "REST API to create a new ImageService"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED",
            content = @Content(mediaType = "application/json")
    )

    @PostMapping("/imageService")
    public ResponseEntity<ResponseDto> addImageService(@RequestBody Image image) {
        imageService.createImageService(image);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ImageServiceConstants.MESSAGE_201, ImageServiceConstants.STATUS_201));
    }

    @Operation(
            summary = "Update ImageService REST API",
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

    @PutMapping("/imageService")
    public ResponseEntity<ResponseDto> updateImageService(@RequestBody Image image) {


        boolean isUpdated = imageService.updateImageService(image);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ImageServiceConstants.MESSAGE_200, ImageServiceConstants.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ImageServiceConstants.MESSAGE_417_UPDATE, ImageServiceConstants.STATUS_417));
        }
    }

    @Operation(
            summary = "Delete ImageService REST API",
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

    @DeleteMapping("/imageService/{imageServiceId}")
    public ResponseEntity<ResponseDto> deleteImageService(@PathVariable Long imageServiceId) {

        boolean isDeleted = imageService.deleteImageService(imageServiceId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ImageServiceConstants.MESSAGE_200, ImageServiceConstants.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ImageServiceConstants.MESSAGE_417_DELETE, ImageServiceConstants.STATUS_417));
        }


    }
    @Operation(
            summary = "Get all Images REST API",
            description = "REST API to get all Tickets"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status successful",
            content = @Content(mediaType = "application/json")
    )

    @GetMapping("/imageService")
    public ResponseEntity<List<Image>>getAllImageService(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(imageService.readAllImageServices());
    }

}

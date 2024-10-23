package de.htwberlin.armanairlines.controller;


import de.htwberlin.armanairlines.constants.TicketConstants;
import de.htwberlin.armanairlines.dto.ErrorResponseDto;
import de.htwberlin.armanairlines.dto.ResponseDto;
import de.htwberlin.armanairlines.dto.TicketDto;
import de.htwberlin.armanairlines.entities.Ticket;
import de.htwberlin.armanairlines.service.ITicketService;
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

@RestController
@RequestMapping("/flightRestAPI")
@Tag(
        name = "CRUD Ticket Rest API",
        description = "CRUD REST API's for our Webapplication to CREATE, UPDATE, FETCH AND DELETE Ticket Details"
)
public class TicketController {


    private ITicketService ticketService;
    @Autowired
    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(
            summary = "Fetch Ticket REST API",
            description = "REST API to fetch a Ticket based on Flight ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/tickets/{flightId}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long flightId){
        TicketDto ticketDto = ticketService.readTicket(flightId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketDto);
    }

    @Operation(
            summary = "Create Ticket REST API",
            description = "REST API to create a new Ticket"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/ticket")
    public ResponseEntity<ResponseDto> addTicket(@RequestBody TicketDto ticketDto){
        ticketService.createTicket(ticketDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(TicketConstants.MESSAGE_201,TicketConstants.STATUS_201));
    }

    @Operation(
            summary = "Update Ticket REST API",
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/ticket")
    public ResponseEntity<ResponseDto> updateTicket(@RequestBody TicketDto ticketDto){
        boolean isUpdated = ticketService.updateTicket(ticketDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TicketConstants.MESSAGE_200,TicketConstants.STATUS_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(TicketConstants.MESSAGE_417_UPDATE,TicketConstants.STATUS_417));
        }
    }

    @Operation(
            summary = "Delete Ticket REST API",
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
    @DeleteMapping("/ticket/{flightId}")
    public ResponseEntity<ResponseDto> deleteTicket(@PathVariable Long flightId){
        boolean isDeleted = ticketService.deleteTicket(flightId);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TicketConstants.MESSAGE_200,TicketConstants.STATUS_200));
        }else{
            return  ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(TicketConstants.MESSAGE_417_DELETE,TicketConstants.STATUS_417));
        }
    }
}

package de.htwberlin.usermanagementservice.controller;


import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")


@Tag(
        name = "Ticket Controller",
        description = "CRUD REST API's for our Webapplication to CREATE, UPDATE, FETCH AND DELETE Tickets Details,only authenticated users can access these endpoints"
)
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(
            summary = "Fetch all Tickets REST API",
            description = "REST API to fetch a all Tickets based on an email. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{email}")
    public List<Ticket> getAllTicketsByUser(@PathVariable String email) {
        return ticketService.getAllTicketsByUser(email);
    }

    @Operation(
            summary = "Fetch Ticket REST API",
            description = "REST API to fetch a single Ticket based on an email. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Integer ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @Operation(
            summary = "Delete Ticket REST API",
            description = "REST API to delete a Ticket based on an email. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {
        ticketService.deleteTicket(ticketId);
    }


    @Operation(
            summary = "Create Ticket REST API",
            description = "REST API to create a Ticket "
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public List<Ticket> createTickets(@RequestBody RequestResponse requestResponse) {
        return ticketService.createTickets(requestResponse.getTickets(), requestResponse.getEmail());
    }
}
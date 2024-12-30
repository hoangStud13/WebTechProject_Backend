package de.htwberlin.usermanagementservice.controller;


import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Alle Tickets eines bestimmten Benutzers zurückgeben, nur für authentifizierte Benutzer
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{email}")
    public List<Ticket> getAllTicketsByUser(@PathVariable String email) {
        return ticketService.getAllTicketsByUser(email);
    }

    // Ticket nach ID suchen, nur für authentifizierte Benutzer
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Integer ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    // Ticket löschen, nur für authentifizierte Benutzer
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {
        ticketService.deleteTicket(ticketId);
    }


    // Liste von Tickets erstellen, nur für authentifizierte Benutzer
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public List<Ticket> createTickets(@RequestBody RequestResponse requestResponse) {
        return ticketService.createTickets(requestResponse.getTickets(), requestResponse.getEmail());
    }
}
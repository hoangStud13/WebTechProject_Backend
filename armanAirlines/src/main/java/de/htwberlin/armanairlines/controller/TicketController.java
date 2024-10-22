package de.htwberlin.armanairlines.controller;


import de.htwberlin.armanairlines.dto.TicketDto;
import de.htwberlin.armanairlines.entities.Ticket;
import de.htwberlin.armanairlines.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightRestAPI")
public class TicketController {


    private ITicketService ticketService;
    @Autowired
    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets/{flightId}")
    public TicketDto getTicket(@PathVariable Long flightId){
        TicketDto ticketDto = ticketService.readTicket(flightId);
        return ticketDto;
    }

    @PostMapping("/ticket")
    public void addTicket(@RequestBody TicketDto ticketDto){
        ticketService.createTicket(ticketDto);
    }

    @PutMapping("/ticket")
    public void updateTicket(@RequestBody TicketDto ticketDto){
        ticketService.updateTicket(ticketDto);
    }

    @DeleteMapping("/ticket/{flightId}")
    public void deleteTicket(@PathVariable Long flightId){
        ticketService.deleteTicket(flightId);
    }
}

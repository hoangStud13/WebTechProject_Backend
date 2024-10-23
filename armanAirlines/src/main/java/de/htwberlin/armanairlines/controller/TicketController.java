package de.htwberlin.armanairlines.controller;


import de.htwberlin.armanairlines.constants.TicketConstants;
import de.htwberlin.armanairlines.dto.ResponseDto;
import de.htwberlin.armanairlines.dto.TicketDto;
import de.htwberlin.armanairlines.entities.Ticket;
import de.htwberlin.armanairlines.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long flightId){
        TicketDto ticketDto = ticketService.readTicket(flightId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketDto);
    }

    @PostMapping("/ticket")
    public ResponseEntity<ResponseDto> addTicket(@RequestBody TicketDto ticketDto){
        ticketService.createTicket(ticketDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(TicketConstants.MESSAGE_201,TicketConstants.STATUS_201));
    }

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

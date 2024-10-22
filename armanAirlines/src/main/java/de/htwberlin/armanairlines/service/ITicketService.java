package de.htwberlin.armanairlines.service;


import de.htwberlin.armanairlines.dto.TicketDto;
import org.springframework.stereotype.Service;


public interface ITicketService {


    void createTicket(TicketDto ticketDto);


    TicketDto readTicket(Long flightId);


    boolean updateTicket(TicketDto ticketDto);

    boolean deleteTicket(Long flightId);


}

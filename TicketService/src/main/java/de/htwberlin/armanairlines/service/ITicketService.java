package de.htwberlin.armanairlines.service;


import de.htwberlin.armanairlines.dto.TicketDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ITicketService {


    void createTicket(TicketDto ticketDto);


    TicketDto readTicket(Long flightId);


    boolean updateTicket(TicketDto ticketDto);

    boolean deleteTicket(Long flightId);

    List<TicketDto> readAllTickets();

}

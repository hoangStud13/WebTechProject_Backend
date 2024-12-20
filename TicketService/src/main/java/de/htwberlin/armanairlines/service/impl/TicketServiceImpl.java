package de.htwberlin.armanairlines.service.impl;

import de.htwberlin.armanairlines.dto.TicketDto;
import de.htwberlin.armanairlines.entities.Ticket;
import de.htwberlin.armanairlines.exception.ResourceNotFoundException;
import de.htwberlin.armanairlines.mapper.TicketMapper;
import de.htwberlin.armanairlines.repository.TicketRepository;
import de.htwberlin.armanairlines.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {


    TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void createTicket(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto,new Ticket());
        ticketRepository.save(ticket);
    }

    @Override
    public TicketDto readTicket(Long flightId) {
        Ticket ticket = ticketRepository.findByFlightId(flightId).orElseThrow(
                ()->new ResourceNotFoundException("Ticket", "flightId",""+flightId)
        );

        TicketDto ticketDto = TicketMapper.mapToTicketDto(ticket,new TicketDto());
        return ticketDto;
    }

    @Override
    public boolean updateTicket(TicketDto ticketDto) {
        boolean isUpdated = false;


        if(ticketDto != null && ticketDto.getFlightId() != null) {
            // Prüfen, ob Ticket existiert, andernfalls Exception werfen
            Ticket ticket = ticketRepository.findByFlightId(ticketDto.getFlightId()).orElseThrow(
                    () -> new ResourceNotFoundException("Ticket", "flightId", "" + ticketDto.getFlightId())
            );

            // Mapping vom TicketDto auf Ticket
            ticket = TicketMapper.mapToTicket(ticketDto, ticket);

            // Ticket in der Datenbank aktualisieren
            ticketRepository.save(ticket);
            isUpdated = true;
        } else {
            throw new IllegalArgumentException("TicketDto or flightId must not be null");
        }



        return  isUpdated;
    }


    @Override
    public boolean deleteTicket(Long flightId) {
        Ticket ticket = ticketRepository.findByFlightId(flightId).orElseThrow(
                ()->new ResourceNotFoundException("Ticket", "flightId",""+flightId)
        );

        ticketRepository.delete(ticket);
        return true;
    }

    public List<TicketDto> readAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDtos.add(TicketMapper.mapToTicketDto(ticket, new TicketDto()));
        }
        return ticketDtos;
    }


}


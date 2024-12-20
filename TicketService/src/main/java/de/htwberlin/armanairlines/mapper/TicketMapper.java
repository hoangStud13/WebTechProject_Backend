package de.htwberlin.armanairlines.mapper;

import de.htwberlin.armanairlines.dto.TicketDto;
import de.htwberlin.armanairlines.entities.Ticket;

public class TicketMapper {

    public static TicketDto mapToTicketDto(Ticket ticket, TicketDto ticketDto) {
        ticketDto.setFlightId(ticket.getFlightId());
        ticketDto.setUserId(ticket.getUserId());
        ticketDto.setName(ticket.getName());
        ticketDto.setDeparture(ticket.getDeparture());
        ticketDto.setDestination(ticket.getDestination());
        ticketDto.setGate(ticket.getGate());
        ticketDto.setDate(ticket.getDate());
        ticketDto.setSeat(ticket.getSeat());
        ticketDto.setBoardingTime(ticket.getBoardingTime());
        return ticketDto;
    }

    public static Ticket mapToTicket(TicketDto ticketDto, Ticket ticket) {
        ticket.setFlightId(ticketDto.getFlightId()); // remove?
        ticket.setUserId(ticketDto.getUserId());
        ticket.setName(ticketDto.getName());
        ticket.setDeparture(ticketDto.getDeparture());
        ticket.setDestination(ticketDto.getDestination());
        ticket.setGate(ticketDto.getGate());
        ticket.setDate(ticketDto.getDate());
        ticket.setSeat(ticketDto.getSeat());
        ticket.setBoardingTime(ticketDto.getBoardingTime());
        return ticket;
    }



}

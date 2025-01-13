package de.htwberlin.usermanagementservice.service.impl;

import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.entity.User;
import de.htwberlin.usermanagementservice.repository.TicketRepository;
import de.htwberlin.usermanagementservice.repository.UserRepository;
import de.htwberlin.usermanagementservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    // Alle Tickets eines bestimmten Benutzers zurückgeben
    public List<Ticket> getAllTicketsByUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return ticketRepository.findAllByUserId(user.get().getId());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Ticket nach ID suchen
    public Ticket getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
    }

    // Liste von Tickets erstellen
    public List<Ticket> createTickets(List<Ticket> tickets, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Alle Tickets mit der UserId des gefundenen Benutzers versehen
            for (Ticket ticket : tickets) {
                ticket.setUserId(user.get().getId()); // Setzt den User für jedes Ticket
                ticket.setName(user.get().getLastName()+","+user.get().getFirstName());
            }
            // Speichert alle Tickets in der Datenbank
            return ticketRepository.saveAll(tickets);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Ticket löschen
    public void deleteTicket(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
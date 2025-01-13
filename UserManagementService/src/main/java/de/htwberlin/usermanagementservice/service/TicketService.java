package de.htwberlin.usermanagementservice.service;

import de.htwberlin.usermanagementservice.entity.Ticket;

import java.util.List;
/**
 * Service interface for managing tickets in the user management system.
 * Provides methods for retrieving, creating, and deleting tickets for users.
 */
public interface TicketService {

    /**
     * Retrieves all tickets associated with a user based on their email.
     *
     * @param email The email address of the user whose tickets are to be fetched.
     * @return A list of {@link Ticket} objects associated with the user.
     */
    List<Ticket> getAllTicketsByUser(String email);

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param ticketId The ID of the ticket to be retrieved.
     * @return A {@link Ticket} object corresponding to the specified ticket ID.
     */
    Ticket getTicketById(Integer ticketId);

    /**
     * Creates a list of new tickets for a user identified by their email.
     *
     * @param tickets A list of {@link Ticket} objects to be created.
     * @param email The email address of the user to associate the tickets with.
     * @return A list of created {@link Ticket} objects.
     */
    List<Ticket> createTickets(List<Ticket> tickets, String email);

    /**
     * Deletes a ticket based on its ID.
     * @param ticketId The ID of the ticket to be deleted.
     */
    void deleteTicket(Integer ticketId);
}

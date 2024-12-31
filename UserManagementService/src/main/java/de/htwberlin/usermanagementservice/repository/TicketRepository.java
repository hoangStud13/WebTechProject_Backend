package de.htwberlin.usermanagementservice.repository;

import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Ticket} entities.
 * Provides methods to access and manipulate ticket data in the database.
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    /**
     * Finds all tickets associated with a specific user by their user ID.
     *
     * @param userId The ID of the user whose tickets are to be retrieved.
     * @return A list of {@link Ticket} entities associated with the specified user.
     */
    List<Ticket> findAllByUserId(Integer userId);

}

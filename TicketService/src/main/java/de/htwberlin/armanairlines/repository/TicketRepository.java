package de.htwberlin.armanairlines.repository;
import de.htwberlin.armanairlines.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


    Optional<Ticket> findByFlightId(Long flightId);
}

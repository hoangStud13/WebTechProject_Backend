package de.htwberlin.usermanagementservice.repository;

import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUserId(Integer userId);

}

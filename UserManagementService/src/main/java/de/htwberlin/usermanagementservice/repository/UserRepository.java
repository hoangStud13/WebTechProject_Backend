package de.htwberlin.usermanagementservice.repository;

import de.htwberlin.usermanagementservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Provides methods to access and manipulate user data in the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to be found.
     * @return An {@link Optional} containing the user if found, or empty if no user with the provided email exists.
     */
    Optional<User> findByEmail(String email);
}

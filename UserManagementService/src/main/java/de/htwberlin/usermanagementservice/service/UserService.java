package de.htwberlin.usermanagementservice.service;

import de.htwberlin.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Service class that implements the {@link UserDetailsService} interface to load user details for authentication.
 * This service fetches user data from the repository based on the user's email.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@link UserService} with the specified {@link UserRepository}.
     *
     * @param userRepository The repository used to fetch user data from the database.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their email (username) for authentication purposes.
     *
     * @param username The email address of the user to be loaded.
     * @return A {@link UserDetails} object containing the user information.
     * @throws UsernameNotFoundException If no user is found with the provided email.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

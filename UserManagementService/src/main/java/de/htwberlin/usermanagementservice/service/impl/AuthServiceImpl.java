package de.htwberlin.usermanagementservice.service.impl;

import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.entity.User;
import de.htwberlin.usermanagementservice.repository.TicketRepository;
import de.htwberlin.usermanagementservice.repository.UserRepository;
import de.htwberlin.usermanagementservice.service.AuthService;
import de.htwberlin.usermanagementservice.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository ourUserRepo;

    private final JWTUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TicketRepository ticketRepository;

    @Autowired
    public AuthServiceImpl(UserRepository ourUserRepo, JWTUtils jwtUtils, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TicketRepository ticketRepository) {
        this.ourUserRepo = ourUserRepo;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.ticketRepository = ticketRepository;
    }

    public RequestResponse signUp(RequestResponse requestResponse) {
        RequestResponse newRequestResponse = new RequestResponse();

        try{
            User user = requestResponse.getUser();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(user.getRole());

            User userResult = ourUserRepo.save(user);
            if (requestResponse.getTickets() != null) {
                List<Ticket> tickets = requestResponse.getTickets();
                for (Ticket ticket : tickets) {
                    ticket.setUserId(userResult.getId()); // Setzt den User für jedes Ticket
                }
                ticketRepository.saveAll(tickets);
            }

            if (userResult != null && userResult.getId()>0) {
                newRequestResponse.setUser(userResult);
                newRequestResponse.setMessage("User saved Successfully");
                newRequestResponse.setStatusCode(200);
            }
        }catch (Exception e){
            e.printStackTrace();
            newRequestResponse.setStatusCode(500);
            newRequestResponse.setMessage(e.getMessage());
        }

        return newRequestResponse;
    }


    public RequestResponse signIn(RequestResponse signInRequestResponse) {
        RequestResponse newRequestResponse = new RequestResponse();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestResponse.getEmail(), signInRequestResponse.getPassword()));
            var user = ourUserRepo.findByEmail(signInRequestResponse.getEmail()).orElseThrow();
            System.out.println("User ist: "+user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>() ,user);
            newRequestResponse.setStatusCode(200);
            newRequestResponse.setToken(jwt);
            newRequestResponse.setRefreshToken(refreshToken);
            newRequestResponse.setExpirationTime("24h");
            newRequestResponse.setMessage("Successfully signed In");
        }catch(Exception e){
            e.printStackTrace();
            newRequestResponse.setStatusCode(500);
            newRequestResponse.setMessage(e.getMessage());
        }

        return newRequestResponse;
    }

    public RequestResponse refreshToken(RequestResponse refreshRequestResponse) {
        RequestResponse response = new RequestResponse();
        String ourEmail = jwtUtils.extractUsername(refreshRequestResponse.getToken());
        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshRequestResponse.getToken(),users)) {

            var jwt = jwtUtils.generateToken(users);
            response.setToken(jwt);
            response.setRefreshToken(refreshRequestResponse.getRefreshToken());
            response.setExpirationTime("24hr");
            response.setMessage("Successfully refreshed Token");
            response.setStatusCode(200);

        }
        response.setStatusCode(500);
        return response;
    }

    @Override
    public boolean checkAuth(String token ) {
        String usernameFromToken = jwtUtils.extractUsername(token);

        User user = new User();
        user.setEmail(usernameFromToken);

        if (jwtUtils.isTokenValid(token, user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RequestResponse updateUser(String token, User user) {
        String usernameFromToken = jwtUtils.extractUsername(token);
        System.out.println(usernameFromToken);
        User userDb = ourUserRepo.findByEmail(usernameFromToken).orElseThrow();
        user.setId(userDb.getId());



        if (userDb.getPassword()!=user.getPassword()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        ourUserRepo.save(user);

        RequestResponse response = new RequestResponse();
        response.setStatusCode(200);
        response.setUser(user);
        return new RequestResponse();
    }


}

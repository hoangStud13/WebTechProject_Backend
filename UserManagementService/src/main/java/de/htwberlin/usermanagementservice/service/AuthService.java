package de.htwberlin.usermanagementservice.service;

import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.User;

/**
 * Service Interface for authentication operations such as registration, login, and token refresh.
 * This interface defines the core methods for interacting with authentication functions.
 */
public interface AuthService {


     /**
      * Registers a new user.
      *
      * @param requestResponse The request and response object containing the user data for registration.
      * @return A {@link RequestResponse} object containing the result of the registration process.
      */
     RequestResponse signUp(RequestResponse requestResponse);

     /**
      * Performs the login for a user.
      *
      * @param signInRequestResponse The request and response object containing the login credentials.
      * @return A {@link RequestResponse} object containing the result of the login process and a JWT.
      */
     RequestResponse signIn(RequestResponse signInRequestResponse);


     /**
      * Refreshes the JWT (Refresh Token).
      *
      * @param refreshRequestResponse The request and response object containing the data for refreshing the token.
      * @return A {@link RequestResponse} object containing the new refresh token.
      */
     RequestResponse refreshToken(RequestResponse refreshRequestResponse);

     /**
      * Method to check whether a token is valid or not
      * @param token
      * @return
      */
     boolean checkAuth(String token);

     /**
      * Method to update an user based on his token
      * @param token
      * @return
      */
     public RequestResponse updateUser(String token, User user );


     /**
      * Method to get an user based on his token
      * @param token
      * @return
      */
     public RequestResponse fetchUser(String token);
}
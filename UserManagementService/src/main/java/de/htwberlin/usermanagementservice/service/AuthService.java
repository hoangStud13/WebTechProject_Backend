package de.htwberlin.usermanagementservice.service;

import de.htwberlin.usermanagementservice.dto.RequestResponse;

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


}

package de.htwberlin.usermanagementservice.controller;

import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.User;
import de.htwberlin.usermanagementservice.service.AuthService;
import de.htwberlin.usermanagementservice.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "User/Authentication Controller",
        description = "CRUD REST API's for our Webapplication to CREATE, UPDATE, FETCH AND DELETE User Details, while also authenticating and authorizing"
)
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Register User REST API",
            description = "REST API to register an user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signup(@RequestBody RequestResponse signupRequest) {
        return ResponseEntity.ok(authService.signUp(signupRequest));
    }

    @Operation(
            summary = "LogIn User REST API",
            description = "REST API to login as an user and give back JWT"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/signin")
    public ResponseEntity<RequestResponse> signin(@RequestBody RequestResponse signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @Operation(
            summary = "Refresh JWT REST API",
            description = "REST API to refresh the token of a user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refresh(@RequestBody RequestResponse refreshRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshRequest));
    }


    @Operation(
            summary = "Check User Authentication REST API",
            description = "REST API to check if the user is authenticated by validating the JWT"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/check-auth")
    public ResponseEntity<RequestResponse> checkAuth(@RequestBody RequestResponse checkAuthRequest) {

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setTokenValid(authService.checkAuth(checkAuthRequest.getToken()));
        return  ResponseEntity.ok(requestResponse);
    }

    @Operation(
            summary = "Update User Authentication REST API",
            description = "REST API to update an existing user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/users")
    public ResponseEntity<RequestResponse> updateUser(@RequestBody RequestResponse checkAuthRequest) {

        RequestResponse requestResponse = new RequestResponse();

        User user = checkAuthRequest.getUser();
        user.setRole(checkAuthRequest.getRole());
        requestResponse.setUser(authService.updateUser(checkAuthRequest.getToken(),user).getUser());
        return  ResponseEntity.ok(requestResponse);
    }


    @Operation(
            summary = "FETCH User Authentication REST API",
            description = "REST API to get an  user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/users")
    public ResponseEntity<RequestResponse> fetchUser(@RequestHeader("Authorization") String token) {
        String extractedToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        RequestResponse response = authService.fetchUser(extractedToken);
        return ResponseEntity.ok(response);
    }
}

package de.htwberlin.usermanagementservice.controller;

import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.service.AuthService;
import de.htwberlin.usermanagementservice.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

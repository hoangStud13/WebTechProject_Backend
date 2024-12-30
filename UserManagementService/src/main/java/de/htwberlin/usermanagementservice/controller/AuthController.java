package de.htwberlin.usermanagementservice.controller;

import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signup(@RequestBody RequestResponse signupRequest) {
        return ResponseEntity.ok(authService.signUp(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<RequestResponse> signin(@RequestBody RequestResponse signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refresh(@RequestBody RequestResponse refreshRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshRequest));
    }
}

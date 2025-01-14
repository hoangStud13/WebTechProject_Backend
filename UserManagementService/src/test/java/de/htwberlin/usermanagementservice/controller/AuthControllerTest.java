package de.htwberlin.usermanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.usermanagementservice.dto.RequestResponse;
import de.htwberlin.usermanagementservice.entity.User;
import de.htwberlin.usermanagementservice.service.AuthService;
import de.htwberlin.usermanagementservice.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    @Mock
    private AuthServiceImpl authService;

    private AuthController authController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private User testUser;
    private RequestResponse testRequestResponse;
    private static final String BASE_PATH = "/api/v1/auth";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);  // Now works with AuthService interface
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        // Test user setup
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        testUser.setPassword("password123");
        testUser.setRole("USER");

        // Test response setup
        testRequestResponse = new RequestResponse();
        testRequestResponse.setStatusCode(200);
        testRequestResponse.setMessage("Success");
        testRequestResponse.setUser(testUser);
    }
    @Test
    @DisplayName("Sign Up - Success")
    void signupSuccess() throws Exception {
        RequestResponse signupRequest = new RequestResponse();
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setRole("USER");

        when(authService.signUp(any(RequestResponse.class))).thenReturn(testRequestResponse);

        mockMvc.perform(post(BASE_PATH + "/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    @DisplayName("Sign In - Success")
    void signinSuccess() throws Exception {
        RequestResponse signInRequest = new RequestResponse();
        signInRequest.setEmail("test@example.com");
        signInRequest.setPassword("password123");

        RequestResponse response = new RequestResponse();
        response.setToken("test.jwt.token");
        response.setRefreshToken("test.refresh.token");
        response.setStatusCode(200);

        when(authService.signIn(any(RequestResponse.class))).thenReturn(response);

        mockMvc.perform(post(BASE_PATH + "/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test.jwt.token"))
                .andExpect(jsonPath("$.refreshToken").value("test.refresh.token"));
    }

    @Test
    @DisplayName("Refresh Token - Success")
    void refreshTokenSuccess() throws Exception {
        RequestResponse refreshRequest = new RequestResponse();
        refreshRequest.setToken("old.jwt.token");
        refreshRequest.setRefreshToken("old.refresh.token");

        RequestResponse response = new RequestResponse();
        response.setToken("new.jwt.token");
        response.setRefreshToken("new.refresh.token");
        response.setStatusCode(200);

        when(authService.refreshToken(any(RequestResponse.class))).thenReturn(response);

        mockMvc.perform(post(BASE_PATH + "/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("new.jwt.token"))
                .andExpect(jsonPath("$.refreshToken").value("new.refresh.token"));
    }

    @Test
    @DisplayName("Check Auth - Valid Token")
    void checkAuthSuccess() throws Exception {
        RequestResponse checkAuthRequest = new RequestResponse();
        checkAuthRequest.setToken("valid.jwt.token");

        when(authService.checkAuth(any(String.class))).thenReturn(true);

        mockMvc.perform(post(BASE_PATH + "/check-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(checkAuthRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenValid").value(true));
    }



    @Test
    @DisplayName("Fetch User - Success")
    void fetchUserSuccess() throws Exception {
        when(authService.fetchUser(any(String.class))).thenReturn(testRequestResponse);

        mockMvc.perform(get(BASE_PATH + "/users")
                        .header("Authorization", "Bearer valid.jwt.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value("test@example.com"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    @DisplayName("Sign Up - Invalid Request")
    void signupInvalidRequest() throws Exception {
        RequestResponse signupRequest = new RequestResponse();
        // Empty request to test validation

        mockMvc.perform(post(BASE_PATH + "/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Fetch User - Invalid Token")
    void fetchUserInvalidToken() throws Exception {
        RequestResponse errorResponse = new RequestResponse();
        errorResponse.setStatusCode(401);
        errorResponse.setError("Unauthorized");
        errorResponse.setMessage("Invalid token");

        when(authService.fetchUser(any(String.class))).thenReturn(errorResponse);

        mockMvc.perform(get(BASE_PATH + "/users")
                        .header("Authorization", "Bearer invalid.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }
}
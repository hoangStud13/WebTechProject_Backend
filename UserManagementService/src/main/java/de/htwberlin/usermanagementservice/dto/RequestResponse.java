package de.htwberlin.usermanagementservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.htwberlin.usermanagementservice.entity.Ticket;
import de.htwberlin.usermanagementservice.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Deserializing, ignoriert die Felder in denen nichts geschrieben wurde
@JsonInclude(JsonInclude.Include.NON_NULL) // Serializing, ignoriert die Werte die keinen Wert haben
public class RequestResponse {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private User user;
    private List<Ticket> tickets;
}

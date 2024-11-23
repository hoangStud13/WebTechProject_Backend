package de.htwberlin.flightsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDto {
    private String statusMessage;
    private String statusCode;
}

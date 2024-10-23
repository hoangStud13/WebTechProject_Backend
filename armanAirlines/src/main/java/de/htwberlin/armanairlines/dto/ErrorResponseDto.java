package de.htwberlin.armanairlines.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name ="ErrorResponseDto",

        description = "This schema is going to give back the apiPath,errorMessage,errorCode and the Timestamp, should an error occur"
)
public class ErrorResponseDto {

    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime timestamp;
}

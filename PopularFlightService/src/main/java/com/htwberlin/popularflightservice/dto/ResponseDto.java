package com.htwberlin.popularflightservice.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name ="ResponseDto",
        description = "This schema is going to give back the HTTPStatus Code and the Statusmessage"
)
public class ResponseDto {

    private String statusMessage;
    private String statusCode;
}

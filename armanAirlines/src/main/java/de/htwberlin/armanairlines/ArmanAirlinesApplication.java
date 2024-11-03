package de.htwberlin.armanairlines;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

@OpenAPIDefinition(

		info = @Info(
				title = "Tickets REST API Documentation",
				description = "Ticket for Flight Application microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Flight Dev Team",
						email = "flight@example.com"
				)

		)

)

public class ArmanAirlinesApplication {


	public static void main(String[] args) {
		SpringApplication.run(ArmanAirlinesApplication.class, args);
	}

}

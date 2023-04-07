package com.smithexperience.springbootrestfulwebservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Spring Boot REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Katrina",
                        email = "smith.katrina.it@gmail.com",
                        url = "https://github.com/SmithKatrinaIT/Spring-Boot-RESTful-Services"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot User Management Documentation",
                url = "https://www.theSmithExperience.com/user_management.html"
        )
)
public class SpringbootRestfulWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestfulWebserviceApplication.class, args);
    }

}

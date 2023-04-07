package com.smithexperience.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * DTO [data transfer object] pattern in Spring Boot application
 * -- the 3-layer architecture in Spring Boot is normally --> Controller, Service, and Repository layers [the repository layer then talks to the Database]
 * --JPA entities are used to map an object with the relational database table and the Repository layer or DAO layer [which can expose sensitive
 *      data between the client and the server causing a security issue]
 * --A client can be Postman or any UI related application [web app, mobile app, desktop app or IOS app]
 * --DTO can be leveraged to transfer data between the client and the server -- it will keep ONLY the required data the client expect in the response of the
 *      REST API
 *      -- also can reduce the number of REMOTE calls to the server [which can be expense]
 *      -- instead of the client calling individual API calls (i.e. Organizational details, Department details, Employee details), a DTO can configure
 *          the different data points and Transfer that date in ONE API call
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    /**
     * @NotEmpty, @NotNull, @Email, etc are Validation Annotations
     * by default they have a 'message' attribute that can be leveraged for custom validation error messages when an
     * Exception is thrown
     */


    // BRQ: User first name should not be null or empty
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    // BRQ: User last name should not be null or empty
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    // BRQ: User email should not be null or empty AND Email address should be valid
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;
}

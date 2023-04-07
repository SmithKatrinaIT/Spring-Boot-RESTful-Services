package com.smithexperience.springbootrestfulwebservices.controller;

import com.smithexperience.springbootrestfulwebservices.dto.UserDto;
import com.smithexperience.springbootrestfulwebservices.entity.User;
import com.smithexperience.springbootrestfulwebservices.exception.ErrorDetails;
import com.smithexperience.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.smithexperience.springbootrestfulwebservices.services.UserDtoRefactorService;
import com.smithexperience.springbootrestfulwebservices.services.UserMapstructDtoRefactorService;
import com.smithexperience.springbootrestfulwebservices.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/refactor/users")
public class UserDTORefactorController {
    /**
     * This class is REFACTORED TO use DTOs to transfer data from the client to the server
     * INSTEAD of using the JPA Entities
     */

    //private UserDtoRefactorService userDtoService;
    private UserMapstructDtoRefactorService userDtoService;

    //build create User REST API
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto saveUser = userDtoService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userDtoService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // build Get All User REST API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userDtoService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto user){

        /**
         * Business [service] logic for updateUser only passes in a User object --> User updateUser(User user);
         * And the Request URL [@PutMapping("{id}")] is passing just an ID
         * Therefore; we need to SET the 'user' Object ID [passed in] to the 'userId' passed in from the request URL
         * If we don't we would get a NullPointerException
         */
        user.setId(userId);
        UserDto updatedUser = userDtoService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userDtoService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }


    /**
     * Option 1: Non-global way of ErrorHandling
     * -- handle the specific exceptions with respect to a controller. [errors for this specific controller methods only]
     * Using ResponseEntity of ErrorDetails object: method should take in the 2 parameters:
     *      1) Type of Exception to handle
     *      2) Details from the Request --> using a native WebRequest object
     *
     * Note: webRequest return the "path" of the request, but the .getDescription(T/F) will return all the client details if set to "true"
     *      --most time this is set to "false"
     *
     * @ExceptionHandler annotation is used to handle the specific exceptions and sending the custom responses to the client
     */
   /* @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        //custom response to the client when error occurs
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND");

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/
}

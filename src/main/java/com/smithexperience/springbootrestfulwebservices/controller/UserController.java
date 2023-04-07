package com.smithexperience.springbootrestfulwebservices.controller;

import com.smithexperience.springbootrestfulwebservices.entity.User;
import com.smithexperience.springbootrestfulwebservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    /**
     * This class is used to create the REST APIs
     * Inject the SERVICE(S) that contain the business logic to execute the APIs purpose and intent
     *  --when configuring APIs, remember the CONTROLLER layer depends on the SERVICE layer [business logic]
     */

    private UserService userService;

    //build create User REST API
    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // build Get All User REST API
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){

        /**
         * Business [service] logic for updateUser only passes in a User object --> User updateUser(User user);
         * And the Request URL [@PutMapping("{id}")] is passing just an ID
         * Therefore; we need to SET the 'user' Object ID [passed in] to the 'userId' passed in from the request URL
         * If we don't we would get a NullPointerException
         */
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}

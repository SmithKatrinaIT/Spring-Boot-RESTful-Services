package com.smithexperience.springbootrestfulwebservices.services.impl;

import com.smithexperience.springbootrestfulwebservices.entity.User;
import com.smithexperience.springbootrestfulwebservices.repository.UserRepository;
import com.smithexperience.springbootrestfulwebservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Service [implementation] classes are used to DEFINE business logic of an API
     * -- Normal practice is to create the Service interface and stub the methods
     * -- then create an Impl [implementation] class and define the logic
     * -- also then INJECT the SERVICE dependency in the CONTROLLER class to call the appropriate methods
     *
     * Using Constructor Dependency Injection; UserRepository dependency will be injected into the UserSerivceImpl [bean] constructor
     * via Lombok @AllArgsConstructor
     * Since Spring 4.3; a single parameterized constructor does not require the @Autowire annotation on the dependency
     *
     * IMPORTANT NOTE: The UserRepository methods calls in this class are being pulled from extending JpaRepository in the UserRepository class
     */
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        /**
         * first get existing User by their ID; the 'user' object passed in has all the user data
         * using Jpa 'extended' methods to get the database user details and save it to the 'existingUser' object
         */
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}

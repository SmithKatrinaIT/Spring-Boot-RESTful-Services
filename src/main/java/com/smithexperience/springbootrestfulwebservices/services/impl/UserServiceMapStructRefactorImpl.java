package com.smithexperience.springbootrestfulwebservices.services.impl;

import com.smithexperience.springbootrestfulwebservices.dto.UserDto;
import com.smithexperience.springbootrestfulwebservices.entity.User;
import com.smithexperience.springbootrestfulwebservices.exception.EmailAlreadyExistException;
import com.smithexperience.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.smithexperience.springbootrestfulwebservices.mapper.AutoUserMapStructMapper;
import com.smithexperience.springbootrestfulwebservices.repository.UserRepository;
import com.smithexperience.springbootrestfulwebservices.services.UserMapstructDtoRefactorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanismException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceMapStructRefactorImpl implements UserMapstructDtoRefactorService{

    /**
     * With the DTO pattern in the Service Implementation class
     * We need to convert the [User] DTO object to a [User Jpa Entity in order to store [persist] the object in the database
     * Ways to convert:
     *  -- 1) lengthy code at every object reference
     *  -- 2) custom mapper class with static methods, containing the conversion logic
     *  -- 3) 3rd Party Libraries: ModelMapper or MapStruct
     *
     *  This SERVICE Implementation class uses the MapStruct example
     */
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {

        //EmailAlreadyExistException
        Optional<User> optionalUserEmail = userRepository.findByEmail(userDto.getEmail());
        if(optionalUserEmail.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exists for User");
        }

        /**
         * // convert UserDto to User Jpa Entity
         * option 1:
         *      User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
         */
        //MapStruct
        User user = AutoUserMapStructMapper.MAPPER.mapToUserJPAEntity(userDto);

        User savedUser = userRepository.save(user);

        /**
         * //convert User JPA entity to UserDto
         * option 1:
         *      UserDto savedUserDto = new UserDto(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
         */
        //MapStruct
        UserDto savedUserDto = AutoUserMapStructMapper.MAPPER.mapToUserDto(savedUser);

        // REFACTORED to use custom Mapper class
        // User user1 = UserDtoToJpaMapper.mapToUserJPAEntity(userDto);
        // User savedUser1 = userRepository.save(user);
        // UserDto savedUserDto1 = UserDtoToJpaMapper.mapToUserDto(user);
        // return savedUserDto1;

        return savedUserDto;
    }

    /**
     * The following code leverages a custom [user] Mapper class to define common "DTO to Jpa conversion" logic
     * LIKE the createUser(UserDto userDto) method above
     */
    @Override
    public UserDto getUserById(Long userId) {
        //Optional<User> optionalUser = userRepository.findById(userId);
        //User user = optionalUser.get(); <-- no longer need as .orElseThrow returns a User object

        //implementing Exception Handling
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId )
        );


        //option 2: return UserDtoToJpaMapper.mapToUserDto(user);
        //Mapstruct
        return AutoUserMapStructMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        /**
         * .stream().map() methods contain the logic to convert the List of Users
         * the custom Mapper method will convert the JPA entity to DTO
         */

        //option 2: return users.stream().map(UserDtoToJpaMapper::mapToUserDto).collect(Collectors.toList());
        //Mapstruct
        return users.stream().map((user) -> AutoUserMapStructMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        /**
         * first get existing User by their ID; the 'user' object passed in has all the user data
         * using Jpa 'extended' methods to get the database user details and save it to the 'existingUser' object
         */
        // User existingUser = userRepository.findById(userDto.getId()).get();

        // Implementing Exception Handling
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //option2: return UserDtoToJpaMapper.mapToUserDto(updatedUser); //only had to map User JPA entity to User DTO
        //Mapstruct
        return AutoUserMapStructMapper.MAPPER.mapToUserDto(updatedUser); //only had to map User JPA entity to User DTO
    }

    @Override
    public void deleteUser(Long userId) {

        // Implementing Exception Handling
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }

}

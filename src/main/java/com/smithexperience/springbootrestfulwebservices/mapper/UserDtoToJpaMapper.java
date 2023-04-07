package com.smithexperience.springbootrestfulwebservices.mapper;

import com.smithexperience.springbootrestfulwebservices.dto.UserDto;
import com.smithexperience.springbootrestfulwebservices.entity.User;

public class UserDtoToJpaMapper {

    // Convert User JPA Entity into UserDTO
    public static UserDto mapToUserDto(User user) {

        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return userDto;
    }

    //Convert UserDTO into User JPA Entity
    public static User mapToUserJPAEntity(UserDto userDto){
        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        return user;
    }
}

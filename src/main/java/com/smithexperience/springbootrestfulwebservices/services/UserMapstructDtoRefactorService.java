package com.smithexperience.springbootrestfulwebservices.services;

import com.smithexperience.springbootrestfulwebservices.dto.UserDto;

import java.util.List;

public interface UserMapstructDtoRefactorService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);
}

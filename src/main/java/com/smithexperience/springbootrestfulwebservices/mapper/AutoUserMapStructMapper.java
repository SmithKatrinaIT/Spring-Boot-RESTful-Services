package com.smithexperience.springbootrestfulwebservices.mapper;

import com.smithexperience.springbootrestfulwebservices.dto.UserDto;
import com.smithexperience.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapStructMapper {

    /**
     *  Define the Mapping methods: MapStruct will create implementation for these methods at compilation time
     *  Add the @Mapper annotation from the org.mapstruc.Mapper package
     *
     *  NOTE: In order to Map one object to another object -- both objects should have the same field names
     *  --if they DO NOT, MapStruct provides annotations to handle these use cases
     *  --i.e If UserDTO field name is email but User field name is emailAddress the following annotation should be used
     *
     *          @Mapping(source = "email", target= "emailAddress")
     *          --where sources is the main object and target is the "mapTo" object
     *
     *   In order to use this MapStruct INTERFACE we need to create an instance [object], but implementation is done at compile time
     *   Therefore, we need to leverage MapStruct's utility class: Mappers.getMapper(MapperInterfaceClassName), i.e AutoUserMapStructMapper.class
     *   by creating an instance [object] of the utility class in the mapper interface.
     *   Then we can use that INSTANCE [declared & initialized in the custom mapper interface] in the SERVICE IMPL class
      */

    AutoUserMapStructMapper MAPPER = Mappers.getMapper(AutoUserMapStructMapper.class);

    //convert User to UserDTO
    //@Mapping(source = "email", target= "emailAddress")
    UserDto mapToUserDto(User user);

    //Convert UserDTO into User JPA Entity
    User mapToUserJPAEntity(UserDto userDto);
}

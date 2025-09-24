package com.spcodage.mappers;

import com.spcodage.dtos.UserDto;
import com.spcodage.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserEntity(UserDto userDto);
    UserDto toUserDto(User user);


}

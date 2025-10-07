package com.spcodage.mappers;

import com.spcodage.dtos.UserDto;
import com.spcodage.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",uses= {RoleMapper.class, PermissionMapper.class,DesignationMapper.class},
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(UserDto userDto);
    UserDto toUserDto(User user);

}

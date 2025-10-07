package com.spcodage.mappers;

import com.spcodage.dtos.RoleDto;
import com.spcodage.entities.Role;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",uses = PermissionMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
   // @Mapping(target = "roleName", source = "roleName")
    RoleDto toRoleDto(Role role);
   //@Mapping(target = "roleName", source = "roleName")
    Role toRoleEntity(RoleDto roleDto);
}

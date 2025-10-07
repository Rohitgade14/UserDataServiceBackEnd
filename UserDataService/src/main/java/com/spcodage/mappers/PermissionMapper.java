package com.spcodage.mappers;

import com.spcodage.dtos.PermissionDto;
import com.spcodage.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

   // @Mapping(target = "permissionName", source = "permissionName")
    PermissionDto toPermissionDto(Permission permission);
//@Mapping(target = "permissionName", source = "permissionName")
    Permission toPermissionEntity(PermissionDto permissionDto);
}

package com.spcodage.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {
    private Integer roleId;
    private String roleName;
    private Set<PermissionDto> permissions;
    private Set<DesignationDto> designations;
}

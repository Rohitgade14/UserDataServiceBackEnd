package com.spcodage.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spcodage.entities.Role;
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
public class DesignationDto {

    private Integer designationId;
    private String designationName;
    private Set<PermissionDto> permissions;
}

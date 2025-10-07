package com.spcodage.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spcodage.entities.Designation;
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
public class PermissionDto {

    private  Integer permissionId;
    private  String permissionName;
    private Set<DesignationDto> designations;

}

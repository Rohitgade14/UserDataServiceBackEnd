package com.spcodage.mappers;

import com.spcodage.dtos.DesignationDto;
import com.spcodage.entities.Designation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DesignationMapper {
   // @Mapping(target = "designationName", source = "designationName")
    DesignationDto toDesignationDto(Designation designation);
  //  @Mapping(target = "designationName", source = "designationName")
    Designation toDesignationEntity(DesignationDto designationDto);
}

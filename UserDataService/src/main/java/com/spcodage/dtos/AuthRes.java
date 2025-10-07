package com.spcodage.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResToken {
   private String accessToken;
   private String  refreshToken;
  // private String Role;
   private UserDto user;




}

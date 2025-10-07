package com.spcodage.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthRes {
   private String accessToken;
   private String  refreshToken;
   private UserDto user;




}

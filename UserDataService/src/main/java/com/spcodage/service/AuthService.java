package com.spcodage.service;



import com.spcodage.dtos.AuthReq;
import com.spcodage.dtos.AuthRes;
import com.spcodage.dtos.UserDto;

public interface AuthService {
    UserDto createUser(UserDto userDto);
    AuthRes loginReq(AuthReq authReq);


}

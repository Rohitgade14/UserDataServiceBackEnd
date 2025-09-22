package com.spcodage.service;

import com.spcodage.dtos.UserDto;

import java.util.List;

public interface UserService {

     UserDto createUser(UserDto userDto);
     List<UserDto> getAllUsers();
     UserDto getUser(Integer userId);
     UserDto updateUser(UserDto userDto,Integer userId);
     void deleteUser(Integer userId);

}

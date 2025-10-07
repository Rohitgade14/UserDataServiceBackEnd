package com.spcodage.service;


import com.spcodage.dtos.UserDto;
import com.spcodage.entities.Role;

import java.util.List;

public interface UserService {


     List<UserDto> getAllUsers();
     UserDto getUser(Integer userId);
     UserDto updateUser(UserDto userDto,Integer userId);
     void deleteUser(Integer userId);
     //String changePassword(String password);


}

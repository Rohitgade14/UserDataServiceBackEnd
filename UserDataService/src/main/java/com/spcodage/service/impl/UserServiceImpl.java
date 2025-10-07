package com.spcodage.service.impl;

import com.spcodage.dtos.PermissionDto;
import com.spcodage.dtos.UserDto;
import com.spcodage.entities.User;
import com.spcodage.exceptions.ResourceNotFoundException;
import com.spcodage.mappers.UserMapper;
import com.spcodage.repository.UserRepository;
import com.spcodage.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        //List<User> users = userRepository.findAll(); // predefined Jpa Method
        List<User> users = userRepository.findByIsDeletedFalse();
        return users.stream().map(userMapper::toUserDto)
                .toList();

    }


//    @Override
//    public UserDto getUser(Integer userId) {

    /// /        User user = userRepository.findById(userId) // predefined Jpa Method
//        User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User Not Found With UserId: " + userId));
//
//        UserDto userDto = userMapper.toUserDto(user);
//
//        return userDto;
//    }
    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer userId) {
//        User user = userRepository.findById(userId) // predefined Jpa Method
        User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found With UserId: " + userId));

        UserDto userDto = userMapper.toUserDto(user);

        Set<PermissionDto> combinedPermission = new HashSet<>(userDto.getPermissions());
        log.info("users permission: {}",userDto.getPermissions());
           userDto.getRoles().forEach( role ->{
                  if(role.getPermissions()!=null){
                      combinedPermission.addAll(role.getPermissions());
                  }
                   });

         userDto.setPermissions(combinedPermission);
         return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
//        User user = userRepository.findById(userId) // predefined Jpa Method
        log.info("Login Req By Email: {}", userDto.getEmail());
        User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("For Update User Not Found With UserId: " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return userMapper.toUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId: " + userId));
        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new ResourceNotFoundException("User is Already is Deleted with UserId: " + userId);
        }
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    public void deleteUser1(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not Found" + userId);
        }
        userRepository.deleteById(userId);

    }

    public void deleteUser2(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with this Id:" + userId));

        userRepository.delete(user);
        userRepository.deleteById(userId);


    }


//    @Override
//    public String changePassword(String password) {
//
//        return "";
//    }


}

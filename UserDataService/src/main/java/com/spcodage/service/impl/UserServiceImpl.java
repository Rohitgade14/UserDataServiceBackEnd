package com.spcodage.service.impl;

import com.spcodage.dtos.UserDto;
import com.spcodage.entities.User;
import com.spcodage.exceptions.ResourceNotFoundException;
import com.spcodage.mappers.UserMapper;
import com.spcodage.repository.UserRepository;
import com.spcodage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
          if(userRepository.existsByEmail(userDto.getEmail())){
              throw  new ResourceNotFoundException("User With given Email: "+userDto.getEmail()+" Already exist");
          }
        User user = userMapper.toUserEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        //List<User> users = userRepository.findAll(); // predefined Jpa Method
        List<User> users = userRepository.findByIsDeletedFalse();
        return users.stream().map(userMapper::toUserDto)
                .toList();
    }


    @Override
    public UserDto getUser(Integer userId) {
//        User user = userRepository.findById(userId) // predefined Jpa Method
        User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found With UserId: " + userId));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
//        User user = userRepository.findById(userId) // predefined Jpa Method
        User user = userRepository.findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("For Update User Not Found With UserId: " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
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

    @Override
    public String changePassword(String password) {

        return "";
    }


}

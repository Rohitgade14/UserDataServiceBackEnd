package com.spcodage.service.impl;

import com.spcodage.dtos.LoginReqDto;
import com.spcodage.dtos.LoginResDto;
import com.spcodage.entities.User;
import com.spcodage.exceptions.ResourceNotFoundException;
import com.spcodage.repository.UserRepository;
import com.spcodage.service.LoginService;
import com.spcodage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private  final UserRepository userRepository;

    public LoginResDto loginReq(LoginReqDto loginReqDto) {
        User user = userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email id is Incorrect: " + loginReqDto.getEmail()));
        if(!user.getPassword().equals(loginReqDto.getPassword())){
              throw new ResourceNotFoundException("password is incorrect"+loginReqDto.getPassword());
        }

        return  LoginResDto.builder()
                .email(user.getEmail())
               // .password(user.getPassword())
                .token("dummy token")
                .build();   // with Builder


//        return new LoginResDto(
//                user.getEmail(),
//                null, //
//                "dummy-jwt-token" //  without Builder
//        );

    }
}

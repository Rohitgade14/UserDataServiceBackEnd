package com.spcodage.contollers;

import com.spcodage.dtos.LoginReqDto;
import com.spcodage.dtos.LoginResDto;
import com.spcodage.dtos.UserDto;
import com.spcodage.payload.StandardResponse;
import com.spcodage.service.LoginService;
import com.spcodage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.spcodage.constants.AppConstants.MessageConstant.CREATE;
import static com.spcodage.constants.AppConstants.MessageConstant.LOGIN;
import static com.spcodage.constants.AppConstants.STATUS_SUCCESS;
import static com.spcodage.constants.AppConstants.StatusCodes.CREATED;
import static com.spcodage.constants.AppConstants.StatusCodes.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final  LoginService loginService;
    private  final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<StandardResponse<UserDto>> create(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return ResponseEntity.status(CREATED).body(
                StandardResponse.<UserDto>builder()
                        .statusCode(CREATED)
                        .status(STATUS_SUCCESS)
                        .message(CREATE)
                        .data(user)
                        .build()
        );

    }
    @PostMapping("/login")
    public ResponseEntity<StandardResponse<LoginResDto>> login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginRes = loginService.loginReq(loginReqDto);
        return ResponseEntity.status(OK).body(
                StandardResponse.<LoginResDto>builder()
                        .statusCode(OK)
                        .status(STATUS_SUCCESS)
                        .message(LOGIN)
                        .data(loginRes)
                        .build()
        );
    }

}

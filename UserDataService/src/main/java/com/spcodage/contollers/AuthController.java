package com.spcodage.contollers;


import com.spcodage.dtos.AuthReq;
import com.spcodage.dtos.AuthRes;
import com.spcodage.dtos.UserDto;
import com.spcodage.payload.StandardResponse;
import com.spcodage.service.AuthService;
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

    private  final AuthService authService;


      @GetMapping("/get")
     public  String get(){
         return  "hello";
     }
    @PostMapping("/register")
    public ResponseEntity<StandardResponse<UserDto>> create(@RequestBody UserDto userDto) {
        UserDto user = authService.createUser(userDto);
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
    public  ResponseEntity<StandardResponse<AuthRes>> login(@RequestBody AuthReq authReq){
         AuthRes authRes = authService.loginReq(authReq);
         return  ResponseEntity.status(OK).body(
                 StandardResponse.<AuthRes>builder()
                         .statusCode(OK)
                         .status(STATUS_SUCCESS)
                         .message(LOGIN)
                         .data(authRes)
                         .build()
         );
     }

//     @PostMapping("/refreshtoken")
//    public  ResponseEntity refresh(){
//          return  null;
//
//     }


}


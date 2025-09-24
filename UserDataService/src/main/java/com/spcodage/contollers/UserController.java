package com.spcodage.contollers;


import com.spcodage.dtos.UserDto;
import com.spcodage.payload.StandardResponse;
import com.spcodage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spcodage.constants.AppConstants.MessageConstant.*;
import static com.spcodage.constants.AppConstants.STATUS_SUCCESS;
import static com.spcodage.constants.AppConstants.StatusCodes.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;



      @GetMapping
       public  ResponseEntity<StandardResponse<List<UserDto>>> getAllUsers(){
          List<UserDto> users = userService.getAllUsers();
          return  ResponseEntity.status(OK).body(
                       StandardResponse.<List<UserDto>>builder()
                               .statusCode(OK)
                               .status(STATUS_SUCCESS)
                               .message(FETHCHE_ALL)
                               .data(users)
                               .build()
               );
      }

        @GetMapping("/{userId}")
      public ResponseEntity<StandardResponse<UserDto>>   getUser(@PathVariable Integer userId){
            UserDto user = userService.getUser(userId);
            return  ResponseEntity.status(OK).body(
                    StandardResponse.<UserDto>builder()
                            .statusCode(OK)
                            .status(STATUS_SUCCESS)
                            .message(FETHCHED)
                            .data(user)
                            .build()
            );
        }

        @PutMapping("/{userId}")
    public  ResponseEntity<StandardResponse<UserDto>> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
            UserDto user = userService.updateUser(userDto, userId);
              return  ResponseEntity.status(OK).body(
                      StandardResponse.<UserDto>builder()
                              .statusCode(OK)
                              .status(STATUS_SUCCESS)
                              .message(UPDATE)
                              .data(user)
                              .build()
              );
        }


           @DeleteMapping("/{userId}")
       public  ResponseEntity<StandardResponse<Void>> deleteUser(@PathVariable Integer userId){
                          userService.deleteUser(userId);
                          return  ResponseEntity.status(OK).body(
                                 StandardResponse.<Void>builder()
                                         .statusCode(NO_CONTENT)
                                         .status(STATUS_SUCCESS)
                                         .message(DELETE)
                                         .build()
                          );
           }





}

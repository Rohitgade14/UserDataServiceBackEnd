package com.spcodage.contollers;


import com.spcodage.dtos.UserDto;
import com.spcodage.payload.StandardResponse;
import com.spcodage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spcodage.constants.AppConstants.MessageConstant.*;
import static com.spcodage.constants.AppConstants.STATUS_SUCCESS;
import static com.spcodage.constants.AppConstants.StatusCodes.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.status(OK).body(
                StandardResponse.<List<UserDto>>builder()
                        .statusCode(OK)
                        .status(STATUS_SUCCESS)
                        .message(FETHCHE_ALL)
                        .data(users)
                        .build()
        );
    }

    @GetMapping("/{userId}")
   // @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<StandardResponse<UserDto>> getUser(@PathVariable Integer userId) {
        UserDto user = userService.getUser(userId);
//        log.info("user Details: {}",user.getRoles());
//        log.info("user: {}",user.getRoles());
//        log.info("user: {}",user.getPermissions());
//        log.info("user: {}",user.getPermissions().getClass());
//        log.info("user: {}",user.getDesignations());
//        log.info("user: {}:",user.getDesignations().getClass());


        System.out.println(user.getPermissions());
        return ResponseEntity.status(OK).body(
                StandardResponse.<UserDto>builder()
                        .statusCode(OK)
                        .status(STATUS_SUCCESS)
                        .message(FETHCHED)
                        .data(user)
                        .build()
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<StandardResponse<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
        UserDto user = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(
                StandardResponse.<UserDto>builder()
                        .statusCode(OK)
                        .status(STATUS_SUCCESS)
                        .message(UPDATE)
                        .data(user)
                        .build()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<StandardResponse<Void>> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(NO_CONTENT).body(
                StandardResponse.<Void>builder()
                        .statusCode(NO_CONTENT)
                        .status(STATUS_SUCCESS)
                        .message(DELETE)
                        .build()
        );
    }


}

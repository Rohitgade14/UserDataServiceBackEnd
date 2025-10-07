package com.spcodage.exceptions;

import com.spcodage.constants.AppConstants;
import com.spcodage.payload.StandardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                StandardResponse.builder()
                        .statusCode(AppConstants.StatusCodes.NOT_FOUND)
                        .status(AppConstants.STATUS_FAILED)
                        .message(ex.getMessage())
                        .data(null)
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    // 401 Unauthorized
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardResponse<?>> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardResponse.builder()
                        .statusCode(AppConstants.StatusCodes.UNAUTHORIZED)
                        .status(AppConstants.STATUS_FAILED)
                        .message("Unauthorized: Please log in again")
                        .data(null)
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }


    // 403 - Access Denied
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardResponse<?>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardResponse.builder()
                        .statusCode(AppConstants.StatusCodes.FORBIDEEN)
                        .status(AppConstants.STATUS_FAILED)
                        .message("Access denied: You do not have permission to access this resource")
                        .data(null)
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    // 409 Conflict
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardResponse<?>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        return new ResponseEntity<>(
                StandardResponse.builder()
                        .statusCode(AppConstants.StatusCodes.CONFLICT)
                        .status(AppConstants.STATUS_FAILED)
                        .message(ex.getMessage())
                        .data(null)
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<?>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                StandardResponse.builder()
                        .statusCode(AppConstants.StatusCodes.INTERNAL_SERVER_ERROR)
                        .status(AppConstants.STATUS_FAILED)
                        .message("Something Went Wrong" + ex.getMessage())
                        .data(null)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

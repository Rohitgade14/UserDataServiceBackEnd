package com.spcodage.exceptions;

import com.spcodage.constants.AppConstants;
import com.spcodage.payload.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

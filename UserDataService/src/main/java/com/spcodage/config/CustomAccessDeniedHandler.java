package com.spcodage.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

import java.time.LocalDateTime;

@Slf4j
@Component
@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // this class handled 403 error

    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        log.warn("Access denied: {}", request.getRequestURI());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json");

        String json = """
            {
              "timestamp": "%s",
              "status": 403,
              "error": "Forbidden",
              "message": "You don't have permission to access this resource",
              "path": "%s"
            }
            """.formatted(LocalDateTime.now(), request.getRequestURI());

        response.getWriter().write(json);
    }


}

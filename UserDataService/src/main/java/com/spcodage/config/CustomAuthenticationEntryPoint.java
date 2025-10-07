package com.spcodage.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
//@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // this class is for 401 error
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        log.warn("Unauthorized access: {}", request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String json = """
            {
              "timestamp": "%s",
              "status": 401,
              "error": "Unauthorized",
              "message": "Invalid or missing authentication token",
              "path": "%s"
            }
            """.formatted(LocalDateTime.now(), request.getRequestURI());

        response.getWriter().write(json);
    }


}

package com.example.Ayurveda_Management.security;

import com.example.Ayurveda_Management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // Use @Lazy to break circular dependency at runtime
    @Autowired
    @Lazy
    private UserService userService;

    public CustomAuthenticationSuccessHandler(UserService theUserService) {
        userService = theUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        String username = authentication.getName();

        userService.findByUsername(username).ifPresent(user -> {
            // Update last login using UserService
            userService.updateLastLogin(user);
        });
    }

}
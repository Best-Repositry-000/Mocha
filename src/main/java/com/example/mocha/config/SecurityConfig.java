package com.example.mocha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SecurityConfig {
    public static String getCurrentUserMatricNo() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        // Case 1: UserDetails (JWT / Spring Security)
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername(); // matric number
        }

        // Case 2: Simple String principal
        if (principal instanceof String matricNumber) {
            return matricNumber;
        }

        throw new RuntimeException("Unable to resolve current user matric number");
    }
}
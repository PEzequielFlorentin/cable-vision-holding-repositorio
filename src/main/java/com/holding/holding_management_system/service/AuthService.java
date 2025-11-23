package com.holding.holding_management_system.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String validateCredentials(String username, String password) {
        // Placeholder logic for validating credentials
        // Replace with actual database/service validation logic
        if ("admin".equals(username) && "adminpass".equals(password)) {
            return "admin";
        } else if ("asesor".equals(username) && "asesorpass".equals(password)) {
            return "asesor";
        } else if ("vendedor".equals(username) && "vendedorpass".equals(password)) {
            return "vendedor";
        }
        return null; // Invalid credentials
    }
}
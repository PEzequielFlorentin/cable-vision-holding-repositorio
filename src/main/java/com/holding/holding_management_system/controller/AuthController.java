package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletResponse response) throws IOException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("/frontend/login.html?error=true");
            return;
        }

        String role = authService.validateCredentials(username, password);

        if (role == null) {
            // Invalid credentials, redirect to login page with error
            response.sendRedirect("/frontend/login.html?error=true");
        } else {
            // Redirect based on role
            switch (role.toLowerCase()) {
                case "admin":
                    response.sendRedirect("/frontend/dashboard-admin.html");
                    break;
                case "asesor":
                    response.sendRedirect("/frontend/dashboard-asesor.html");
                    break;
                case "vendedor":
                    response.sendRedirect("/frontend/dashboard-vendedor.html");
                    break;
                default:
                    response.sendRedirect("/frontend/login.html?error=true");
            }
        }
    }
}
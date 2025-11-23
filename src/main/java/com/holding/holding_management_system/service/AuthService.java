package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.LoginRequest;
import com.holding.holding_management_system.model.User;
import com.holding.holding_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequest loginRequest) {
        // Buscar el usuario por su nombre de usuario
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar la contraseña
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Generar un token (por ejemplo, JWT) o manejar la sesión
        return "TOKEN_GENERADO"; // Aquí puedes generar un JWT o manejar la sesión
    }
}
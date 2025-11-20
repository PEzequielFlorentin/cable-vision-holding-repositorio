package com.holding.holding_management_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a la consola H2
                .requestMatchers("/api/empresas/**").permitAll() // Permitir acceso público a /api/empresas
                .anyRequest().authenticated() // Requiere autenticación para las demás rutas
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**", "/api/empresas/**") // Deshabilitar CSRF para H2 y /api/empresas
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitir iframes para la consola H2
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/h2-console", true) // Redirige a la consola H2 después de iniciar sesión
            );

        return http.build();
    }
}
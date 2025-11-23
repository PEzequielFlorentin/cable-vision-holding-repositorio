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
                .requestMatchers("/h2-console/**", "/frontend/login.html", "/frontend/css/**").permitAll() // Permitir acceso a login.html y recursos estáticos
                .anyRequest().authenticated() // Requiere autenticación para las demás rutas
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Deshabilitar CSRF para H2
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitir iframes para la consola H2
            )
            .formLogin(form -> form
                .loginPage("/frontend/login.html") // Configura tu página personalizada de login
                .permitAll() // Permitir acceso a la página de login
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/frontend/login.html")
                .permitAll()
            );

        return http.build();
    }
}
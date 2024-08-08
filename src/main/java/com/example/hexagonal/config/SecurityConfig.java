package com.example.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/users/**").permitAll()  // Permite el acceso público a /users/**
                                .anyRequest().authenticated()          // Requiere autenticación para cualquier otro endpoint
                )
                .csrf(csrf -> csrf.disable());  // Desactiva CSRF para simplificar el ejemplo

        return http.build();
    }
}

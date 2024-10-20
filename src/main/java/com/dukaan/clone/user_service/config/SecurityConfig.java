package com.dukaan.clone.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity (optional, but not recommended in production)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))  // Allow H2 console to load in browser frames
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users/register", "/h2-console/**").permitAll()  // Permit access to /register and /h2-console
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .httpBasic();  // This could be replaced by JWT if you're using token-based authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

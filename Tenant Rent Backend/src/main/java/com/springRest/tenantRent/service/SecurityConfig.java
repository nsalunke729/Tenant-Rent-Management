package com.springRest.tenantRent.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF protection for the API
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/login").permitAll() // Public login endpoint
                .anyRequest().authenticated() // Other requests require authentication
            )
            .httpBasic(); // Enable basic authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
            .password("{noop}password") // No password encoding for simplicity
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "https://tenant-rental-app.netlify.app") // Add Netlify domain here
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
}
}

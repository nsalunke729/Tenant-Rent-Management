package com.springRest.tenantRent.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/**").authenticated() // secure API endpoints
                .anyRequest().permitAll() // other requests are permitted
            )
            .httpBasic(); // enable basic authentication

        return http.build();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF protection for the API
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/login").permitAll() // Secure API endpoints
                .anyRequest().authenticated() // Other requests are permitted
            )
            .httpBasic(); // Enable basic authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
            .password("{noop}password") // no password encoding for simplicity
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Update with your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
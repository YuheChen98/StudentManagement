package com.example.studentmanagement.config;

import com.example.studentmanagement.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/**
 * Security configuration class for the Student Management System.
 * This configuration sets up security rules for HTTP requests,
 * enabling JWT authentication and specifying role-based access control
 * for different parts of the application.
 *
 * <p>This class uses {@link org.springframework.security.config.annotation.web.builders.HttpSecurity}
 * to configure the security requirements.</p>
 *
 * <ul>
 *     <li>CSRF protection is disabled to support stateless session management.</li>
 *     <li>Session management is set to stateless which is suitable for REST APIs where
 *     each request needs to be authenticated independently.</li>
 *     <li>URL-based authorization is configured to restrict access based on roles and
 *     to allow public access to certain API endpoints.</li>
 *     <li>A custom {@link com.example.studentmanagement.utils.JwtAuthenticationFilter}
 *     is registered to handle JWT tokens for user authentication.</li>
 * </ul>
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain that applies to HTTP requests.
     *
     * @param http the {@link HttpSecurity} to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during the configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF as JWT is inherently protected against CSRF
        http.csrf(csrf -> csrf.disable());

        // Use stateless session; session won't be used to store user's state.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Setup permissions for various API endpoints
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**","/swagger-ui/**","/v3/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/tutor/**").hasRole("TUTOR")
                .requestMatchers("/lecturer/**").hasRole("LECTURER")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated() // All other requests need authentication
        );

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build and return the Security Filter Chain
        return http.build();


    }

}

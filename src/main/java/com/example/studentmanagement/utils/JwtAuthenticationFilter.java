package com.example.studentmanagement.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Collections;


/**
 * Custom filter that intercepts HTTP requests to authenticate users based on JSON Web Tokens (JWT).
 * This filter extracts the JWT from the Authorization header, where it is expected to be formatted as 'Bearer [token]'.
 * It then validates the token, extracts the user ID and roles, and sets the Spring Security authentication context.
 *
 * <p>This implementation leverages {@link JwtUtils} for JWT parsing and validation tasks, ensuring that only
 * valid and non-expired tokens allow access to secured resources. The filter integrates seamlessly with
 * Spring Security's filter chain by extending {@link org.springframework.web.filter.GenericFilterBean}.</p>
 *
 * <p>Authentication is set in the security context to enable downstream filters and/or methods to enforce
 * authorization checks based on roles provided within the token.</p>
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

    /**
     * Processes each incoming HTTP request, extracts the JWT from the Authorization header, and
     * authenticates the request.
     *
     * @param servletRequest  the request from which to extract parameters and perform the authentication
     * @param servletResponse the response, potentially needed for other filters in the chain
     * @param filterChain     the filter chain that may contain further filters to process the request
     * @throws IOException      if an I/O error occurs during request processing
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = httpRequest.getHeader("Authorization");

        // Check if the Authorization header contains a bearer token
        if (token != null && token.startsWith("Bearer")) {
            // Extract the JWT token from the Authorization header
            String jwt = token.substring(7);

            // Use JwtUtils to decode the token and extract details
            String userId = JwtUtils.getClaimsByToken(jwt).getSubject();
            String role = JwtUtils.extractRoleFromToken(jwt);

            // Create an Authentication object, which Spring Security uses to represent the current authenticated user
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, Collections.singletonList(new SimpleGrantedAuthority(role)));

            // Set the authentication into the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

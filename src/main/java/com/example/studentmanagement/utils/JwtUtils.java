package com.example.studentmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * Utility class for JSON Web Token (JWT) operations in the Student Management System.
 * This class provides methods to generate JWTs for user authentication and to extract details
 * from a given JWT. It utilizes the {@link io.jsonwebtoken.Jwts} class from the jjwt library
 * to perform these operations.
 *
 * <p>The tokens are configured to have a specific expiration time and are secured using the HS512
 * signature algorithm with a predefined secret key.</p>
 *
 * @see io.jsonwebtoken.Claims
 * @see io.jsonwebtoken.Jwts
 * @see io.jsonwebtoken.SignatureAlgorithm
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
public class JwtUtils {
    /**
     * The expiration time for the JWT, in seconds (one week).
     */
    private static long expire = 604800;

    /**
     * The secret key used for signing the JWT. It must be kept private to ensure token integrity and security.
     */
    private static String secret = "abcdfghiabcdfghiabcdfghiabcdfghi";

    /**
     * Generates a JWT for a user based on their login credentials.
     *
     * @param user The user's login credentials containing the user ID and role.
     * @return A string representing the JWT.
     */
    public static String generateToken(LoginRequest user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getUserId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    /**
     * Extracts the claims (payload) from a given JWT.
     *
     * @param token The JWT from which to extract the claims.
     * @return The claims of the specified token.
     */
    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extracts the role of a user from the given JWT.
     *
     * @param token The JWT from which to extract the user role.
     * @return The role of the user as specified in the token's claims.
     */
    public static String extractRoleFromToken(String token) {
        Claims claims = getClaimsByToken(token);
        return (String) claims.get("role");
    }
}

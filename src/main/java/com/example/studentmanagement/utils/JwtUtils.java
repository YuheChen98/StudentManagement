package com.example.studentmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static long expire = 604800;
    private static String secret = "abcdfghiabcdfghiabcdfghiabcdfghi";

    public static String generateToken(LoginRequest user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(user.getUserId())
                .claim("role",user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())
                .compact();
    }

    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    public static String extractRoleFromToken(String token) {
        Claims claims = getClaimsByToken(token);
        return (String) claims.get("role");
    }
}

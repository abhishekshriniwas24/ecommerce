package com.example.ecommerce.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }
    public String generateToken(String email){

        long expirationTime= 1000 * 60 * 60;

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new
                        Date(System.currentTimeMillis()+ expirationTime))
                .signWith(getSigningKey())
                .compact();
    }
}

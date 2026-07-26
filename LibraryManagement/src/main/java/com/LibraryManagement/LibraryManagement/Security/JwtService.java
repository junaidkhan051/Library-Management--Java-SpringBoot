package com.LibraryManagement.LibraryManagement.Security;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private static String secret;

    @Value("${jwt.expiration}")
    private static long jwtExpiration;

    private static SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());

    }
    public static String generateToken(UserDetails userDetails) {

        return Jwts.builder()

                .subject(userDetails.getUsername())

                .issuedAt(new Date())

                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))

                .signWith(getSigningKey())

                .compact();

    }
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

}

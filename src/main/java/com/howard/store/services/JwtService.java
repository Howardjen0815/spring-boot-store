package com.howard.store.services;

import com.howard.store.config.JwtConfig;
import com.howard.store.entities.Role;
import com.howard.store.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;
    public Jwt generateToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }
    public Jwt generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpirtation());
    }

    private Jwt generateToken(User user, long tokenExpiration) {
        var claim = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        return new Jwt(claim, jwtConfig.getSecretKey());

    }
    public Role getRoleFromToken(String token) {
        return Role.valueOf(getCliams(token).get("role").toString());
    }

    private Claims getCliams(String token) {
        return Jwts.parser().verifyWith(jwtConfig.getSecretKey()).build().parseSignedClaims(token).getPayload();
    }
    public Jwt ParseToken(String token) {
        try {
            var claims = getCliams(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        }catch (JwtException e) {
            return null;
        }
    }
}

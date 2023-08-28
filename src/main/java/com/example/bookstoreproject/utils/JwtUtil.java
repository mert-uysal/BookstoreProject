package com.example.bookstoreproject.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateJwtToken(JwtUser jwtUser) {
        final Date createdDate = new Date();
        // one day
        long expirationTimeAsMilliseconds = 86400000 * 100;
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeAsMilliseconds);

        Iterator<? extends GrantedAuthority> iterator = jwtUser.getAuthorities().iterator();

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", jwtUser.getEmail());
        claims.put("name", jwtUser.getName());
        claims.put("role", iterator.next());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(jwtUser.getName())
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public JwtUser getAuthenticatedUserFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            JwtUser jwtUser = new JwtUser();
            jwtUser.setName(claims.get("name").toString());
            jwtUser.setEmail(claims.get("email").toString());

            return jwtUser;

        } catch (Exception e) {
            log.error("Exception while parsing jwt token", e);
            return null;
        }
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            log.error("Token is not valid", e);
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}

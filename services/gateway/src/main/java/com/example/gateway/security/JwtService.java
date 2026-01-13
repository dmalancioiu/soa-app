package com.example.gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final Key key;

  public JwtService(@Value("${jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String createToken(String username) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusSeconds(3600)))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}

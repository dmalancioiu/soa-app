package com.example.gateway.controller;

import com.example.gateway.security.JwtService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final JwtService jwtService;

  public AuthController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> payload) {
    String username = payload.getOrDefault("username", "");
    String password = payload.getOrDefault("password", "");
    if ("demo".equals(username) && "demo123".equals(password)) {
      return ResponseEntity.ok(Map.of("token", jwtService.createToken(username)));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Map.of("error", "Invalid credentials"));
  }
}

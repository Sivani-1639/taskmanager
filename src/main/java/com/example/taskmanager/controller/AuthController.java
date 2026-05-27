package com.example.taskmanager.controller;

import com.example.taskmanager.dto.AuthDtos.*;
import com.example.taskmanager.model.User;
import com.example.taskmanager.security.JwtService;
import com.example.taskmanager.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService users;
    private final JwtService jwt;

    public AuthController(UserService users, JwtService jwt) {
        this.users = users;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        User u = users.register(
                req.username(),
                req.password()
        );

        String token = jwt.generate(req.username());

        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        req.username()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest req
    ) {

        return ResponseEntity.ok(
                new AuthResponse(
                        jwt.generate(req.username()),
                        req.username()
                )
        );
    }
}
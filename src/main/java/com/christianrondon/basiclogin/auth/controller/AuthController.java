package com.christianrondon.basiclogin.auth.controller;

import com.christianrondon.basiclogin.auth.dto.RegisterRequest;
import com.christianrondon.basiclogin.auth.service.AuthService;
import com.christianrondon.basiclogin.auth.dto.LoginRequest;
import com.christianrondon.basiclogin.auth.dto.LoginResponse;
import com.christianrondon.basiclogin.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        User user = authService.validateCredentials(request);
        String token = authService.createToken(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest request) {
        return authService.registerUser(request);
    }


}

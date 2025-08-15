package com.christianrondon.basiclogin.auth.service;

import com.christianrondon.basiclogin.auth.dto.LoginRequest;
import com.christianrondon.basiclogin.auth.dto.RegisterRequest;
import com.christianrondon.basiclogin.entity.Role;
import com.christianrondon.basiclogin.entity.Token;
import com.christianrondon.basiclogin.entity.User;
import com.christianrondon.basiclogin.enums.RoleEnum;
import com.christianrondon.basiclogin.repository.RoleRepository;
import com.christianrondon.basiclogin.repository.TokenRepository;
import com.christianrondon.basiclogin.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       TokenRepository tokenRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    public User validateCredentials(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }

        return user;
    }

    public String createToken(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(1);

        Token tokenCreated = new Token();

        tokenCreated.setUser(user);
        tokenCreated.setValue(token);
        tokenCreated.setExpiresAt(expiration);
        tokenCreated.setActive(true);

        tokenRepository.save(tokenCreated);

        return token;
    }

    public boolean validateToken(String tokenValue) {
        Token token = tokenRepository.findByValue(tokenValue)
                .orElseThrow(() -> new RuntimeException("Invalid Token"));

        if (!token.isActive()) {
            throw new RuntimeException("Token is inactive");
        }

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Session is expired");

        }
        return true;
    }

    public ResponseEntity<String> registerUser(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Role roleDefault = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Invalid Role"));

        User userRegistered = new User();
        userRegistered.setName(request.getName());
        userRegistered.setEmail(request.getEmail());
        userRegistered.setPassword(passwordEncoder.encode(request.getPassword()));
        userRegistered.setRole(roleDefault);

        userRepository.save(userRegistered);

        return ResponseEntity.status(HttpStatus.CREATED).body("You're welcome " + userRegistered.getName());

    }

}

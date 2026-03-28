package dev.eduardo.security.controller;

import dev.eduardo.security.config.TokenConfig;
import dev.eduardo.security.dto.request.LoginRequest;
import dev.eduardo.security.dto.request.RegisterUserRequest;
import dev.eduardo.security.dto.response.LoginResponse;
import dev.eduardo.security.dto.response.RegisterUserResponse;
import dev.eduardo.security.entity.User;
import dev.eduardo.security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        User newUser = new  User();
        newUser.setPassword(passwordEncoder.encode(registerUserRequest.password()));
        newUser.setName(registerUserRequest.name());
        newUser.setEmail(registerUserRequest.email());

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getName(), newUser.getEmail()));
    }
}

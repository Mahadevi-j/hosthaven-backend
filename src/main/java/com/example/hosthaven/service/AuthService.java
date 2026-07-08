package com.example.hosthaven.service;

import com.example.hosthaven.dto.AuthRequestDto;
import com.example.hosthaven.dto.AuthResponseDto;
import com.example.hosthaven.dto.RegisterRequestDto;
import com.example.hosthaven.entity.SystemUser;
import com.example.hosthaven.exception.BusinessValidationException;
import com.example.hosthaven.repository.SystemUserRepository;
import com.example.hosthaven.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(SystemUserRepository systemUserRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register User
    public AuthResponseDto register(RegisterRequestDto dto) {

        if (systemUserRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BusinessValidationException("Username already exists");
        }

        SystemUser user = new SystemUser();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole());

        systemUserRepository.save(user);

        AuthResponseDto response = new AuthResponseDto();
        response.setMessage("User Registered Successfully");
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());

        return response;
    }

    // Login User
    public AuthResponseDto login(AuthRequestDto dto) {

        SystemUser user = systemUserRepository.findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new BusinessValidationException("Invalid Username"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessValidationException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        AuthResponseDto response = new AuthResponseDto();
        response.setMessage("Login Successful");
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setToken(token);

        return response;
    }
}
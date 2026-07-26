package com.LibraryManagement.LibraryManagement.Service;



import com.LibraryManagement.LibraryManagement.Repository.UserRepo;
import com.LibraryManagement.LibraryManagement.Security.JwtService;
import com.LibraryManagement.LibraryManagement.dto.AuthResponse;
import com.LibraryManagement.LibraryManagement.dto.LoginRequest;
import com.LibraryManagement.LibraryManagement.dto.RegisterRequest;
import com.LibraryManagement.LibraryManagement.entity.user;

import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;

@Service
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user users = user.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(users);

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        users.getUsername(),
                        users.getPassword(),
                        java.util.List.of(
                                new org.springframework.security.core.authority.SimpleGrantedAuthority(
                                        "ROLE_" + users.getRole()
                                )
                        )
                );

        String token = JwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request, JwtService JwtService, JMXAuthenticator authenticationManager, CachingUserDetailsService userDetailsService) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        String token = JwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
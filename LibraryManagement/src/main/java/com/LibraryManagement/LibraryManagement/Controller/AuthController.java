package com.LibraryManagement.LibraryManagement.Controller;



import com.LibraryManagement.LibraryManagement.Security.JwtService;
import com.LibraryManagement.LibraryManagement.dto.AuthResponse;
import com.LibraryManagement.LibraryManagement.dto.LoginRequest;
import com.LibraryManagement.LibraryManagement.dto.RegisterRequest;
import com.LibraryManagement.LibraryManagement.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) {

        System.out.println("Register API Hit");

        return ResponseEntity.ok(authService.register(request));
    }

}

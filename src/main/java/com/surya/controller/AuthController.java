package com.surya.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surya.exception.UserException;
import com.surya.payload.dto.UserDTO;
import com.surya.payload.request.ForgotPasswordRequest;
import com.surya.payload.request.LoginRequest;
import com.surya.payload.request.ResetPasswordRequest;
import com.surya.payload.response.ApiResponse;
import com.surya.payload.response.AuthResponse;
import com.surya.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
        @RequestBody @Valid UserDTO req
    ) throws UserException {
        AuthResponse res = authService.signup(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
        @RequestBody @Valid LoginRequest req
    ) throws UserException {
        AuthResponse res = authService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword (
        @RequestBody ForgotPasswordRequest request
    ) throws UserException {
        authService.createPasswordResetToken(request.getEmail());

        ApiResponse res = new ApiResponse("Check your email for password reset link", true);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword (
        @RequestBody ResetPasswordRequest request
    ) throws Exception {
        authService.resetPassword(request.getToken(), request.getPassword());
        ApiResponse res = new ApiResponse("Password reset successfully", true);
        return ResponseEntity.ok(res);
    }
    


    
    
    
}

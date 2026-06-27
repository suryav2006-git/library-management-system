package com.surya.services;

import com.surya.exception.UserException;
import com.surya.payload.dto.UserDTO;
import com.surya.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;

    void createPasswordResetToken(String email) throws UserException;
    void resetPassword(String token, String newPassword) throws Exception;
    
} 

package com.surya.payload.dto;

import java.time.LocalDateTime;

import com.surya.domain.UserRole;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Email is Mandatory")
    private String email;

    @NotNull(message = "Password is Mandatory")
    private String password;

    private String phone;

    @NotNull(message = "Full Name is Mandatory")
    private String fullName;

    private UserRole role;

    private String username;

    private LocalDateTime lastLogin;



}

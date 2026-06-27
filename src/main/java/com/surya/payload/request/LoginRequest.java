package com.surya.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Username or Email is Mandatory")
    private String username;
    
    @NotNull(message = "Password is Mandatory")
    private String password;

}

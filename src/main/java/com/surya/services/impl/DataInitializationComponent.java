package com.surya.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.surya.domain.UserRole;
import com.surya.modal.User;
import com.surya.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void initializeAdminUser() {
        String adminEmail = "mrstudieshelper@gmail.com";
        String adminPassword = System.getenv("MAIL_PASSWORD");

        if (userRepository.findByEmail(adminEmail) == null) {
            User user = User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName("Mr Studies Helper")
                    .role(UserRole.ROLE_ADMIN)
                    .build();

            userRepository.save(user);
        }
    }

}

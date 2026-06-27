package com.surya.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surya.configurations.JwtProvider;
import com.surya.domain.UserRole;
import com.surya.exception.UserException;
import com.surya.mapper.UserMapper;
import com.surya.modal.PasswordResetToken;
import com.surya.modal.User;
import com.surya.payload.dto.UserDTO;
import com.surya.payload.response.AuthResponse;
import com.surya.repository.PasswordResetTokenRepository;
import com.surya.repository.UserRepository;
import com.surya.services.AuthService;
import com.surya.services.EmailService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImplementation customUserServiceImplementation;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;

    @Override
    public AuthResponse login(String username, String password) throws UserException {
        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // String role = authorities.iterator().next().getAuthority();

        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(username);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Login Success");
        response.setMessage("Welcome Back"+ username);
        response.setJwt(token);
        response.setUser(UserMapper.toDTO(user));

        return response;
    }

    private Authentication authenticate(String username, String password) throws UserException {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);
        
        if(userDetails == null) {
            throw new UserException("User Not Found With Email" + password);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());    
    }

    @Override
    public AuthResponse signup(UserDTO req) throws UserException {
        User user = userRepository.findByEmail(req.getEmail());
        if(user==null) {
            throw new UserException("Email Id Already Registered");
        }
        User createdUser =new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(UserRole.ROLE_USER);

        User savedUser = userRepository.save(createdUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                    savedUser.getEmail(), savedUser.getPassword()
                );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtProvider.generateToken(auth);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setTitle("Welcome"+ createdUser.getFullName());
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));
        
        return authResponse;
    }

    @Transactional
    public void createPasswordResetToken(String email) throws UserException {
        
        String frontendUrl = "";

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserException("User Not Found With Email" + email);
        }

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
            .token(token)
            .user(user)
            .expiryDate(LocalDateTime.now().plusMinutes(5))
            .build();
        passwordResetTokenRepository.save(resetToken);
        String resetLink = frontendUrl+token;
        String subject = "Password Reset Request";
        String body = "Click the link to reset your password: (Valid Only 5 Minutes) " + resetLink;
    
        emailService.sendEmail(user.getEmail(), subject, body);
    
    }

    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
            .orElseThrow(
                () -> new Exception("Invalid or Expired Token")
            );

        if(resetToken.isExpired()) {
            passwordResetTokenRepository.delete(resetToken);
            throw new Exception("Token Expired");
        }
    }
    

}

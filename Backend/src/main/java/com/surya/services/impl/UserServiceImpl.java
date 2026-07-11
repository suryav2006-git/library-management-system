package com.surya.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.surya.mapper.UserMapper;
import com.surya.modal.User;
import com.surya.payload.dto.UserDTO;
import com.surya.repository.UserRepository;
import com.surya.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User Not Found");
        }

        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(
                UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(
                () -> new Exception("User Not Found With Given Id"));
    }

}

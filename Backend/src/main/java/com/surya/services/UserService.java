package com.surya.services;

import java.util.List;

import com.surya.modal.User;
import com.surya.payload.dto.UserDTO;

public interface UserService {

    public User getCurrentUser() throws Exception;

    public List<UserDTO> getAllUsers();

}

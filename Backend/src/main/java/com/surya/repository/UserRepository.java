package com.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

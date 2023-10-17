package com.project.cinematek.repository;

import com.project.cinematek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByUsername(String username);
}

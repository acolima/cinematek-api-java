package com.project.cinematek.controller;

import com.project.cinematek.dto.UserDTO;
import com.project.cinematek.model.User;
import com.project.cinematek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository repository;
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userData){
        String username = userData.username();
        String password = userData.password();
        String picture = userData.picture();

        if(username == null || password == null || picture == null)
            return new ResponseEntity<>("All fields are required", HttpStatus.UNPROCESSABLE_ENTITY);

        if(repository.findByUsername(username) != null)
            return new ResponseEntity<>("This username is already in use.", HttpStatus.CONFLICT);

        String hashedPassword = new BCryptPasswordEncoder().encode(password);
        User newUser = new User(username, hashedPassword, picture);

        repository.save(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

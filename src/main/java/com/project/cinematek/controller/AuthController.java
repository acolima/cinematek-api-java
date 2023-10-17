package com.project.cinematek.controller;

import com.project.cinematek.dto.AuthenticationDTO;
import com.project.cinematek.dto.UserDTO;
import com.project.cinematek.model.User;
import com.project.cinematek.repository.UserRepository;
import com.project.cinematek.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository repository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO userData){
        if(repository.findByUsername(userData.username()) != null)
            return ResponseEntity.badRequest().build();

        String hashedPassword = new BCryptPasswordEncoder().encode(userData.password());
        User newUser = new User(userData.username(), hashedPassword, userData.picture());

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO userData){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
}

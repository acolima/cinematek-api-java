package com.project.cinematek.controller;

import com.project.cinematek.config.token.TokenService;
import com.project.cinematek.model.Movie;
import com.project.cinematek.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    TokenService tokenService;

    @PostMapping("{action}/{status}")
    public ResponseEntity<String> addMovie(@PathVariable String action, @PathVariable String status, @RequestBody Movie movie) {
        Integer userId = tokenService.getIdFromToken();

        boolean statusBool = status.equals("true");

        movieService.saveMovie(userId, movie, action, statusBool);

        return ResponseEntity.ok().build();
    }

}

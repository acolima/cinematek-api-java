package com.project.cinematek.controller;

import com.project.cinematek.config.token.TokenService;
import com.project.cinematek.model.Movie;
import com.project.cinematek.model.UserMovie;
import com.project.cinematek.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("{id}")
    public ResponseEntity<UserMovie> getMovie(@PathVariable String id) {
        Integer movieId = Integer.parseInt(id);
        Integer userId = tokenService.getIdFromToken();

        UserMovie movie =  movieService.getMovie(movieId, userId);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Movie>>> getMovies() {
        Integer userId = tokenService.getIdFromToken();

        Map<String, List<Movie>> movies = movieService.getMovies(userId);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Movie>> getMoviesFromCategory(@PathVariable String category) {
        Integer userId = tokenService.getIdFromToken();

        Map<String, List<Movie>> movies = movieService.getMovies(userId);

        return new ResponseEntity<>(movies.get(category), HttpStatus.OK);
    }
}

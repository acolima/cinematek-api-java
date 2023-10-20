package com.project.cinematek.service;

import com.project.cinematek.model.Movie;
import com.project.cinematek.model.UserMovie;
import com.project.cinematek.repository.MovieRepository;
import com.project.cinematek.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserMovieRepository userMovieRepository;

    public void saveMovie(Integer userId, Movie movie, String action, boolean status) {
        movieRepository.save(movie);

        UserMovie userMovie = userMovieRepository.findByUserIdAndMovieId(userId, movie.getId());

        if(userMovie == null) {
            UserMovie newUserMovie = new UserMovie(movie.getId(), userId, action, status);
            userMovieRepository.save(newUserMovie);
        } else {
            userMovie.updateCategory(action, status);
            UserMovie updatedMovie = userMovieRepository.save(userMovie);

            if(!updatedMovie.isFavorite() && !updatedMovie.isWatched() && !updatedMovie.isWatchlist())
                userMovieRepository.delete(updatedMovie);
        }
    }

    public UserMovie getMovie(Integer movieId, Integer userId) {
        return userMovieRepository.findByUserIdAndMovieId(userId, movieId);
    }

    public Map<String, List<Movie>> getMovies(Integer userId) {
        List<Movie> favoriteMovies = movieRepository.getFavoriteMovies(userId);
        List<Movie> watchedMovies = movieRepository.getWatchedMovies(userId);
        List<Movie> watchlistMovies = movieRepository.getWatchlistMovies(userId);

        Map<String, List<Movie>> movies = new HashMap<>();

        movies.put("watched", watchedMovies);
        movies.put("favorite", favoriteMovies);
        movies.put("watchlist", watchlistMovies);

        return movies;
    }
}

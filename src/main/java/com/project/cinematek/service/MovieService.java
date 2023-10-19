package com.project.cinematek.service;

import com.project.cinematek.model.Movie;
import com.project.cinematek.model.UserMovie;
import com.project.cinematek.repository.MovieRepository;
import com.project.cinematek.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            if(!updatedMovie.isFavorite() && !updatedMovie.isWatched() && !updatedMovie.isWatchlist()) {
                userMovieRepository.delete(updatedMovie);
            }
        }


    }
}

package com.project.cinematek.repository;

import com.project.cinematek.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT tmdb_id, backdrop_path, poster_path, title\n" +
            "\tFROM movie m\n" +
            "INNER JOIN user_movie u\n" +
            "ON m.tmdb_id=u.movie_id\n" +
            "WHERE u.user_id=?1 AND watched",
            nativeQuery = true)
    List<Movie> getWatchedMovies(Integer userId);

    @Query(value = "SELECT tmdb_id, backdrop_path, poster_path, title\n" +
            "\tFROM movie m\n" +
            "INNER JOIN user_movie u\n" +
            "ON m.tmdb_id=u.movie_id\n" +
            "WHERE u.user_id=?1 AND watchlist",
            nativeQuery = true)
    List<Movie> getWatchlistMovies(Integer userId);

    @Query(value = "SELECT tmdb_id, backdrop_path, poster_path, title\n" +
            "\tFROM movie m\n" +
            "INNER JOIN user_movie u\n" +
            "ON m.tmdb_id=u.movie_id\n" +
            "WHERE u.user_id=?1 AND favorite",
            nativeQuery = true)
    List<Movie> getFavoriteMovies(Integer userId);
}

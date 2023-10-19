package com.project.cinematek.repository;

import com.project.cinematek.model.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {
    UserMovie findByUserIdAndMovieId(Integer userId, Integer movieId);
}

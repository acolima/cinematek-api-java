package com.project.cinematek.repository;

import com.project.cinematek.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {}

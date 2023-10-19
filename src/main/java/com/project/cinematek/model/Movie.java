package com.project.cinematek.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private Integer tmdbId;
    private String title;
    private String posterPath;
    private String backdropPath;

    public Integer getId() {
        return tmdbId;
    }
}

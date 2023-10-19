package com.project.cinematek.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="user_movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMovie {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer movieId;
    private boolean favorite;
    private boolean watched;
    private boolean watchlist;
    @UpdateTimestamp
    private LocalDateTime modifyAt;

    public UserMovie(Integer movieId, Integer userId, String action, boolean status) {
        this.userId = userId;
        this.movieId = movieId;
        this.updateCategory(action, status);
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isWatched() {
        return watched;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void updateCategory(String action, boolean status) {
        if(action.equals("watched")) watched = status;
        if(action.equals("favorite")) favorite = status;
        if(action.equals("watchlist")) watchlist = status;
    }
}

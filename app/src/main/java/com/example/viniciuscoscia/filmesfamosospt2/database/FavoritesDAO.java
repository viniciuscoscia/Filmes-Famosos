package com.example.viniciuscoscia.filmesfamosospt2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;

import java.util.List;

@Dao
public interface FavoritesDAO {
    @Query("SELECT * FROM favorite_movies ORDER BY id")
    LiveData<List<Movie>> loadAllFavoriteMovies();

    @Insert
    void insertFavoriteMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavoriteMovie(Movie movie);

    @Delete
    void deleteFavoriteMovie(Movie movie);

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    Movie loadFavoriteMovieById(long id);
}

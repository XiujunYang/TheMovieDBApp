package com.example.themoviedbapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.themoviedbapp.model.gson.Movie;

import java.util.List;

import static com.example.themoviedbapp.util.AppConstant.DB_TABLE_NAME;

/**
 * Created by Jean on 2018/3/11.
 */

@Dao
public interface MovieDao {
    @Query("SELECT * FROM " + DB_TABLE_NAME)
    List<Movie> getAll();

    @Query("SELECT * FROM " + DB_TABLE_NAME + " WHERE id IN (:movieIds)")
    List<Movie> loadAllByIds(int[] movieIds);

    @Query("SELECT * FROM " + DB_TABLE_NAME + " WHERE title LIKE '%' || :title || '%' OR " + "original_title LIKE  '%' || :title || '%'")
    List<Movie> findMoviesByTitle(String title);

    @Query("SELECT * FROM " + DB_TABLE_NAME + "  WHERE title = :title LIMIT 1")
    Movie findMovieByTitle(String title);

    @Insert
    void insertAll(Movie... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<Movie> movies);

    @Delete
    void delete(Movie user);
}

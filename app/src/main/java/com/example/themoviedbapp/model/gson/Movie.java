package com.example.themoviedbapp.model.gson;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.themoviedbapp.util.AppConstant;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import static com.example.themoviedbapp.util.AppConstant.DB_TABLE_NAME;

/**
 * Created by Jean on 2018/3/11.
 */
@Entity(tableName = DB_TABLE_NAME)
@Data
@ToString
public class Movie {
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    @PrimaryKey private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
    @Ignore private List<Integer> genre_ids;
}

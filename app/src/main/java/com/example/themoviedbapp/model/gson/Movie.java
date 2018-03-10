package com.example.themoviedbapp.model.gson;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Jean on 2018/3/10.
 */
@Data
@ToString
public class Movie {
    private int page;
    private int total_results;
    private int total_pages;
    private List<MovieBean> results;

    @Data
    @ToString
    public class MovieBean {
        private String poster_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private int id;
        private String original_title;
        private String original_language;
        private String title;
        private String backdrop_path;
        private double popularity;
        private int vote_count;
        private boolean video;
        private double vote_average;
        private List<Integer> genre_ids;
    }
}

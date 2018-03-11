package com.example.themoviedbapp.model.gson;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Jean on 2018/3/10.
 */
@Data
@ToString
public class MovieResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

}

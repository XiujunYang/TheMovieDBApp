package com.example.themoviedbapp.model.gson;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Jean on 2018/3/10.
 */

@Data
@ToString
public class DetailedMovie {
    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private int budget;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private Object poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    private List<GenresBean> genres;
    private List<ProductionCompaniesBean> production_companies;
    private List<ProductionCountriesBean> production_countries;
    private List<SpokenLanguagesBean> spoken_languages;

    @Data
    public static class GenresBean {
        private int id;
        private String name;
    }

    @Data
    public static class ProductionCompaniesBean {
        private String name;
        private int id;
    }

    @Data
    public static class ProductionCountriesBean {
        private String iso_3166_1;
        private String name;
    }

    @Data
    public static class SpokenLanguagesBean {
        private String iso_639_1;
        private String name;
    }
}

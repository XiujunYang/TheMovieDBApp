package com.example.themoviedbapp.model;

import com.example.themoviedbapp.model.gson.DetailedMovie;
import com.example.themoviedbapp.model.gson.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jean on 2018/3/10.
 */

public interface ApiService {
    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRateMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRateMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRateMovies(@Query("api_key") String apiKey, @Query("page") int page, @Query("language") String language);

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page, @Query("language") String language);

    @GET("/search/movie")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{movie_id}")
    Single<DetailedMovie> getMovieDetail(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);
}

package com.example.themoviedbapp.model;

import com.example.themoviedbapp.model.gson.DetailedMovie;
import com.example.themoviedbapp.model.gson.Movie;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jean on 2018/3/10.
 */

public interface ApiService {
    @GET("movie/top_rated")
    Observable<Movie> getTopRateMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<Movie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Single<DetailedMovie> getMovieDetail(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);
}

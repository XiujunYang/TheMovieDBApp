package com.example.themoviedbapp.model;

import com.example.themoviedbapp.BuildConfig;
import com.example.themoviedbapp.model.gson.MovieResponse;
import com.example.themoviedbapp.util.AppConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.themoviedbapp.util.AppConstant.CONNECT_TO_TIMEOUT_SECOND;

/**
 * Created by Jean on 2018/3/10.
 */

public class APIDataLoader {
    private Logger logger = LoggerFactory.getLogger(APIDataLoader.class);
    ApiService apiService;

    @Inject
    public APIDataLoader() {
        logger.info("DEBUG:" + (BuildConfig.DEBUG == true));
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }
        client.connectTimeout(CONNECT_TO_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TO_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TO_TIMEOUT_SECOND, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.TMDB_DOMAIN_NAME)
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void subscribeTopRateMovies(Observer<MovieResponse> observer) {
        apiService.getTopRateMovies(BuildConfig.TMDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void subscribePopularMovies(Observer<MovieResponse> observer) {
        apiService.getPopularMovies(BuildConfig.TMDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void subscribeSingledMovieDetail(int movieId, SingleObserver observer) {
        apiService.getMovieDetail(movieId, BuildConfig.TMDB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}

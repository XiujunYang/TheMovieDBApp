package com.example.themoviedbapp.presenter;

import android.content.Intent;

import com.example.themoviedbapp.model.APIDataLoader;
import com.example.themoviedbapp.model.gson.DetailedMovie;
import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.view.DetailedMovieActivity;
import com.example.themoviedbapp.view.DetailedMovieActivityInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jean on 2018/3/10.
 */

public class DetailedMoviePresenter {
    private Logger logger = LoggerFactory.getLogger(DetailedMoviePresenter.class);
    private DetailedMovieActivityInterface detailedMovieView;

    public DetailedMoviePresenter(DetailedMovieActivityInterface detailedMovieActivity) {
        detailedMovieView = detailedMovieActivity;
    }

    public void retrieveIntent(Intent intent) {
        int movieId = intent.getIntExtra("movieId", -1);
        if(movieId == -1) ((DetailedMovieActivity)detailedMovieView).finish();
        APIDataLoader dataLoader = new APIDataLoader();
        SingleObserver<DetailedMovie> observer = new SingleObserver<DetailedMovie>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("[onSubscribe]");
            }
            @Override
            public void onError(Throwable e) {
                logger.error("[error]{}", e);
            }
            @Override
            public void onSuccess(DetailedMovie detail) {
                logger.info("[complete]{}", detail);
                detailedMovieView.displayDetail(detail);
            }
        };
        dataLoader.subscribeSingledMovieDetail(movieId, observer);
    }
}

package com.example.themoviedbapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.themoviedbapp.model.APIDataLoader;
import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.view.MainActivity;
import com.example.themoviedbapp.view.MainViewInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jean on 2018/3/9.
 */

public class MainPresenter {
    private Logger logger = LoggerFactory.getLogger(MainPresenter.class);
    private MainViewInterface mainView;
    private Context context;
    private List<Movie.MovieBean> movieList = new CopyOnWriteArrayList<Movie.MovieBean>();
    private PostersAdapter postersAdapter;

    public MainPresenter(MainViewInterface mainView) {
        this.mainView = mainView;
        context = ((MainActivity)mainView).getParent();
        postersAdapter = new PostersAdapter(movieList);
    }

    public void onCreate() {
        mainView.initContentView(postersAdapter);
        APIDataLoader loader = new APIDataLoader();
        Observer<Movie> observer = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger.info("[onSubscribe]");
            }
            @Override
            public void onNext(Movie movie) {
                logger.info("[movie]{}", movie);
                movieList.addAll(movie.getResults());
            }
            @Override
            public void onError(Throwable e) {
                logger.error("[error]{}", e);
            }
            @Override
            public void onComplete() {
                logger.info("[complete]");
                postersAdapter.notifyDataSetChanged();
            }
        };
        loader.subscribeTopRateMovies(observer);
    }
}

package com.example.themoviedbapp.dependencyInjection;

import com.example.themoviedbapp.presenter.DetailedMoviePresenter;
import com.example.themoviedbapp.presenter.MainPresenter;
import com.example.themoviedbapp.view.DetailedMovieActivityInterface;
import com.example.themoviedbapp.view.MainViewInterface;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jean on 2018/3/12.
 */
@Module
public class PresenterModule {

    @Named("main")
    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Named("detail")
    @Provides
    public DetailedMoviePresenter provideDetailedMoviePresenter(DetailedMovieActivityInterface activity) {
        return new DetailedMoviePresenter(activity);
    }
}

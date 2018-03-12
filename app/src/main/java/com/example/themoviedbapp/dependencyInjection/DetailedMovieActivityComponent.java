package com.example.themoviedbapp.dependencyInjection;

import com.example.themoviedbapp.presenter.DetailedMoviePresenter;
import com.example.themoviedbapp.view.DetailedMovieActivity;
import com.example.themoviedbapp.view.DetailedMovieActivityInterface;
import com.example.themoviedbapp.view.MainViewInterface;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Jean on 2018/3/13.
 */
@Component(modules = PresenterModule.class)
public interface DetailedMovieActivityComponent {
    DetailedMoviePresenter detailedMoviePresenter();
    void inject(DetailedMovieActivity detailedMovieActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        DetailedMovieActivityComponent.Builder withParameter(DetailedMovieActivityInterface activity);
        DetailedMovieActivityComponent build();
    }
}
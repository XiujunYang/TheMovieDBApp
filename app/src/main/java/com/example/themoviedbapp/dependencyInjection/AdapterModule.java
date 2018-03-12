package com.example.themoviedbapp.dependencyInjection;

import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.presenter.PostersAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jean on 2018/3/13.
 */
@Module
public class AdapterModule {
    @Provides
    public PostersAdapter providePostersAdapter(List<Movie> list) {
        return new PostersAdapter(list);
    }
}

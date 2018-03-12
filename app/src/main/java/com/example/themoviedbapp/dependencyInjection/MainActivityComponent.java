package com.example.themoviedbapp.dependencyInjection;

import com.example.themoviedbapp.view.MainActivity;

import dagger.Component;

/**
 * Created by Jean on 2018/3/12.
 */

@Component(modules = PresenterModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}

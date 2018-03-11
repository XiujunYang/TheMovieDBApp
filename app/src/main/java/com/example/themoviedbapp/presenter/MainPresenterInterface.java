package com.example.themoviedbapp.presenter;

import android.content.Context;

import com.example.themoviedbapp.view.MainViewInterface;

import dagger.Component;

/**
 * Created by Jean on 2018/3/11.
 */
public interface MainPresenterInterface {
    public void init(MainViewInterface mainView, Context context);
}

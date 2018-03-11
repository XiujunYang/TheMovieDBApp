package com.example.themoviedbapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.themoviedbapp.model.gson.Movie;

/**
 * Created by Jean on 2018/3/11.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}

package com.example.themoviedbapp.dependencyInjection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jean on 2018/3/13.
 */
@Module
public class DataLoaderModule {
    @Provides
    //@Singleton
    public DataLoaderModule provideAPIDataLoader() {
        return new DataLoaderModule();
    }
}

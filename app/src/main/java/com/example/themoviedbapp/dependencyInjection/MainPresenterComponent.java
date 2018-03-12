package com.example.themoviedbapp.dependencyInjection;

import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.presenter.MainPresenter;
import com.example.themoviedbapp.presenter.PostersAdapter;

import java.util.List;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Jean on 2018/3/13.
 */
@Component(modules = {DataLoaderModule.class, AdapterModule.class})
public interface MainPresenterComponent {
    PostersAdapter postersAdapter();
    void inject(MainPresenter presenter);

    @Component.Builder
	interface Builder {
		@BindsInstance Builder withParameter(List<Movie> movieList);
        MainPresenterComponent build();
	}
}

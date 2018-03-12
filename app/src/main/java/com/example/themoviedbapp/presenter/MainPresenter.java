package com.example.themoviedbapp.presenter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.example.themoviedbapp.R;
import com.example.themoviedbapp.dependencyInjection.DaggerMainPresenterComponent;
import com.example.themoviedbapp.model.APIDataLoader;
import com.example.themoviedbapp.model.AppDatabase;
import com.example.themoviedbapp.model.MovieDao;
import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.model.gson.MovieResponse;
import com.example.themoviedbapp.view.DetailedMovieActivity;
import com.example.themoviedbapp.view.MainActivity;
import com.example.themoviedbapp.view.MainViewInterface;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.themoviedbapp.util.AppConstant.DATABASE_NAME;

/**
 * Created by Jean on 2018/3/9.
 */
public class MainPresenter {
    private Logger logger = LoggerFactory.getLogger(MainPresenter.class);

    private List<Movie> movieList = new CopyOnWriteArrayList<Movie>();
    @Inject PostersAdapter postersAdapter;
    @Inject APIDataLoader loader;

    private MainViewInterface mainView;
    private Context context;
    private AppDatabase db;
    private MovieDao movieDAO;
    private int navSatus;

    private NavigationView.OnNavigationItemSelectedListener nvListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_top_movie:
                case R.id.nav_pop_movie:
                    refreshMovieListByCategory(item.getItemId());
                    break;
                case R.id.nav_search_history:
                    // TODO: fix it.
                    //refreshMovieListFromDB();
                    break;
                default:
                    break;
            }
            mainView.closeNavigationDrawer();
            return true;
        }
    };

    Observer<MovieResponse> movieListObserver = new Observer<MovieResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
            logger.info("[onSubscribe]");
        }
        @Override
        public void onNext(MovieResponse movie) {
            logger.info("[movie]{}", movie);
            movieList.addAll(movie.getResults());
            insertDataToDB(movie.getResults());
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

    MaterialSearchView.OnQueryTextListener searchQueryTxtLinstener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            logger.info("[onQueryTextSubmit] query: " + query);
            displayMovieDetailByName(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            findMovieTitleFromDB(newText);
//            mainView.adjustActionBar();
            return true;
        }
    };

    @Inject
    public MainPresenter() {
        DaggerMainPresenterComponent.builder().withParameter(movieList).build().inject(this);
    }

    public void init(MainViewInterface mainView) {
        this.mainView = mainView;
        this.context = ((MainActivity) mainView).getApplicationContext();
        mainView.init(postersAdapter, nvListener, searchQueryTxtLinstener);
        loader.subscribeTopRateMovies(movieListObserver);
    }

    public void refreshMovieListByCategory(int category) {
        movieList.clear();
        this.navSatus = category;
        switch (category) {
            case R.id.nav_pop_movie:
                loader.subscribePopularMovies(movieListObserver);
                break;
            case R.id.nav_top_movie:
            default:
                loader.subscribeTopRateMovies(movieListObserver);
                break;
        }
    }

    private void insertDataToDB(List<Movie> movies) {
        new AsyncTask<List<Movie>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Movie>... params) {
                if(db == null || movieDAO == null) {
                    db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                    movieDAO = db.movieDao();
                }
                movieDAO.insertAllMovies(params[0]);
                return null;
            }
        }.execute(movies);
    }

    private void refreshMovieListFromDB() {
        new AsyncTask<Void, Void, List<Movie>>() {
            @Override
            protected List<Movie> doInBackground(Void... params) {
                if(db == null || movieDAO == null) {
                    db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                    movieDAO = db.movieDao();
                }
                return movieDAO.getAll();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                super.onPostExecute(movies);
                logger.info("[refreshMovieListFromDB] movie.size=" + movies.size());
                movieList.clear();
                movieList.addAll(movies);
                postersAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void findMovieTitleFromDB(String searchTxt) {
        new AsyncTask<String, Void, List<Movie>>() {
            @Override
            protected List<Movie> doInBackground(String... params) {
                if(db == null || movieDAO == null) {
                    db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                    movieDAO = db.movieDao();
                }
                return movieDAO.findMoviesByTitle(params[0]);
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                super.onPostExecute(movies);
                logger.info("[findMovieTitleFromDB] movie.size=" + movies.size());
                List<String> suggestions = new ArrayList<String>();
                // suggestions = movies.stream().map(movie ->  movie.getTitle()).collect(Collectors.toList());
                for (Movie movie: movies) {
                    suggestions.add(movie.getTitle());
                }
                mainView.updateSearchViewSugguestion(suggestions.toArray(new String[suggestions.size()]));
            }
        }.execute(searchTxt);
    }

    private void displayMovieDetailByName(String movieTitle) {
        new AsyncTask<String, Void, Movie>() {
            @Override
            protected Movie doInBackground(String... params) {
                if(db == null || movieDAO == null) {
                    db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                    movieDAO = db.movieDao();
                }
                return movieDAO.findMovieByTitle(params[0]);
            }

            @Override
            protected void onPostExecute(Movie movie) {
                super.onPostExecute(movie);
                if(movie != null) {
                    logger.info("[displayMovieNameDetail] movie.id=" + movie.getId());
                    Intent intent = new Intent(context, DetailedMovieActivity.class);
                    intent.putExtra("movieId", movie.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        }.execute(movieTitle);
    }

    public int getNavStatus() {
        return this.navSatus;
    }
}

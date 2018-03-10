package com.example.themoviedbapp.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.themoviedbapp.R;
import com.example.themoviedbapp.model.APIDataLoader;
import com.example.themoviedbapp.model.gson.DetailedMovie;
import com.example.themoviedbapp.presenter.DetailedMoviePresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.themoviedbapp.util.AppConstant.TMDB_LOADING_IMG_PREFIX_URL;

public class DetailedMovieActivity extends AppCompatActivity implements DetailedMovieActivityInterface{
    private DetailedMoviePresenter presenter = new DetailedMoviePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);
        Intent intent = getIntent();
        presenter.retrieveIntent(intent);
    }

    public void displayDetail(DetailedMovie movie) {
        Fresco.initialize(this);
        SimpleDraweeView poster = findViewById(R.id.detail_imageId);
        Uri uri = Uri.parse(TMDB_LOADING_IMG_PREFIX_URL + movie.getPoster_path());
        poster.setImageURI(uri);
        TextView title = findViewById(R.id.detail_titleId);
        title.setText(movie.getTitle());
        TextView originalTitle = findViewById(R.id.detail_original_titleId);
        originalTitle.setText(movie.getOriginal_title());
        TextView runTime = findViewById(R.id.detail_runTimeId);
        runTime.setText(movie.getRuntime() + " minutes");
        ProgressBar voteAverage = findViewById(R.id.vote_average_bar);
        int percentage = (int)(movie.getVote_average() * 10.0);
        voteAverage.setProgress(percentage);
        TextView voteAverageTxt = findViewById(R.id.txt_vote_average);
        voteAverageTxt.setText(percentage + "%");
        TextView releasedDate = findViewById(R.id.detail_release_date);
        releasedDate.setText(movie.getRelease_date());
        TextView genres = findViewById(R.id.detail_movie_genres);
        List<String> genresList = new ArrayList<String>();
        for(DetailedMovie.GenresBean bean : movie.getGenres()) genresList.add(bean.getName());
        // List<String> genresList = movie.getGenres().stream().map(obj -> obj.getName()).collect(Collectors.<String>toList());
        genres.setText(android.text.TextUtils.join(",", genresList));
        TextView overView = findViewById(R.id.detail_movie_overview);
        overView.setText(movie.getOverview());
    }
}

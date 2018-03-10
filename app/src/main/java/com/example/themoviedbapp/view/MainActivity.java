package com.example.themoviedbapp.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.themoviedbapp.R;
import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.presenter.MainPresenter;
import com.example.themoviedbapp.presenter.PostersAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    private Logger logger = LoggerFactory.getLogger(MainActivity.class);
    //@Inject MainPresenter presenter;
    MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.info("MainActivity onCreate");
        presenter.onCreate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Fresco.initialize(this);
    }

    public void initContentView(PostersAdapter postersAdapter) {
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3)); //gridView
        recyclerView.setAdapter(postersAdapter);
    }
}

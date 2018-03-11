package com.example.themoviedbapp.view;

import android.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.themoviedbapp.R;
import com.example.themoviedbapp.presenter.MainPresenter;
import com.example.themoviedbapp.presenter.PostersAdapter;
import com.example.themoviedbapp.util.Util;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    private Logger logger = LoggerFactory.getLogger(MainActivity.class);
    MainPresenter presenter = new MainPresenter();
    private DrawerLayout mDrawerLayout;
    private MaterialSearchView searchView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.info("MainActivity onCreate");
        setContentView(R.layout.activity_main);
        presenter.init(this, this.getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if(savedInstanceState != null) {
            int oldNavStatus = savedInstanceState.getInt("nav_menu_id");
            presenter.refreshMovieListByCategory(oldNavStatus);
            navigationView.setCheckedItem(oldNavStatus);
        } else {
            ImagePipelineConfig config = Util.getImgPiplineConfig(this);
            Fresco.initialize(this, config);
        }
    }

    public void init(PostersAdapter postersAdapter, NavigationView.OnNavigationItemSelectedListener nvListener,
            MaterialSearchView.OnQueryTextListener searchQueryTxtLinstener) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3)); //gridView
        recyclerView.setAdapter(postersAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(nvListener);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(searchQueryTxtLinstener);
    }

    public void closeNavigationDrawer() {
        mDrawerLayout.closeDrawers();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("nav_menu_id", presenter.getNavStatus());
    }

    public void updateSearchViewSugguestion(String[] suggestions) {
            searchView.setSuggestions(suggestions);
    }

    public void adjustActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
    }
}

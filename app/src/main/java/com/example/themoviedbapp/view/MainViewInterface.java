package com.example.themoviedbapp.view;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.example.themoviedbapp.presenter.PostersAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

/**
 * Created by Jean on 2018/3/9.
 */

public interface MainViewInterface {
    public void init(PostersAdapter postersAdapter, NavigationView.OnNavigationItemSelectedListener nvListener,
            MaterialSearchView.OnQueryTextListener searchQueryTxtLinstener);
    public void closeNavigationDrawer();
    public void updateSearchViewSugguestion(String[] suggestions);
    public void adjustActionBar();
}

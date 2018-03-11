package com.example.themoviedbapp.util;

import com.facebook.common.util.ByteConstants;

/**
 * Created by Jean on 2018/3/9.
 */

public class AppConstant {
    public static final String TMDB_DOMAIN_NAME = "https://api.themoviedb.org/3/";
    public static final String TMDB_LOADING_IMG_PREFIX_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String DATABASE_NAME = "database-tmdbApp";
    public static final String DB_TABLE_NAME = "movies_history";

    public static final int SPLASH_SCREEN_SECONDMILL = 3000;
    public static final int CONNECT_TO_TIMEOUT_SECOND = 10;

    public static final int MAX_DISK_CACHE_VERYLOW_SIZE = 20 * ByteConstants.MB;
    public static final int MAX_DISK_CACHE_LOW_SIZE = 60 * ByteConstants.MB;
    public static final int MAX_DISK_CACHE_SIZE = 100 * ByteConstants.MB;
}

package com.example.themoviedbapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themoviedbapp.R;
import com.example.themoviedbapp.model.gson.Movie;
import com.example.themoviedbapp.view.DetailedMovieActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.example.themoviedbapp.util.AppConstant.TMDB_LOADING_IMG_PREFIX_URL;

/**
 * Created by Jean on 2018/3/10.
 */

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder>{
    private Logger logger = LoggerFactory.getLogger(PostersAdapter.class);

    List<Movie> list;
    Context context;
    public PostersAdapter(List<Movie> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        context = parent.getContext();
        holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.poster_item_layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Uri uri = Uri.parse(TMDB_LOADING_IMG_PREFIX_URL + list.get(position).getPoster_path());
        holder.posterView.setImageURI(uri);
        holder.movieTitle.setText(list.get(position).getTitle());
        logger.info("[onBindViewHolder]{}", list.get(position));
        holder.posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, DetailedMovieActivity.class);
                intent.putExtra("movieId", list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView posterView;
        TextView movieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            posterView = (SimpleDraweeView) itemView.findViewById(R.id.gridView_image_Id);
            movieTitle = (TextView) itemView.findViewById(R.id.gridView_movie_title);
        }
    }
}
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
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;

import static com.example.themoviedbapp.util.AppConstant.TMDB_LOADING_IMG_PREFIX_URL;

/**
 * Created by Jean on 2018/3/10.
 */

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder>{
    private Logger logger = LoggerFactory.getLogger(PostersAdapter.class);

    private List<Movie> list;
    private Context context;
    private ItemClickListener itemClickListener;

    @Inject
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public Movie getItem(int position) {
        return list.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        SimpleDraweeView posterView;
        TextView movieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            posterView = (SimpleDraweeView) itemView.findViewById(R.id.gridView_image_Id);
            posterView.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
            posterView.setOnClickListener(this);
            movieTitle = (TextView) itemView.findViewById(R.id.gridView_movie_title);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }
}
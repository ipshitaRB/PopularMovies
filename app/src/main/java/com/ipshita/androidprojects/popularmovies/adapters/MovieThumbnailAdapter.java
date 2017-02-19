package com.ipshita.androidprojects.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ipshita.androidprojects.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom ArrayAdapter for movie grid view . shows thumbnails fetched using {@link Picasso}
 * Created by Ipshita on 18-02-2017.
 */

public class MovieThumbnailAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private List<Movie> movieList;

    public MovieThumbnailAdapter(Context context, int resource, List<Movie> movieList) {
        super(context, resource, movieList);
        mContext = context;
        this.movieList = movieList;

    }

    public int getCount() {
        if (null != movieList && !movieList.isEmpty())
            return movieList.size();
        else
            return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        if (null != movieList && !movieList.isEmpty()) {
            Movie movie = movieList.get(position);
            if (null != movie.getPosterPath() && !movie.getPosterPath().isEmpty())
                Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"
                        + movie.getPosterPath()).into(imageView);
            //imageView.setImageResource(movieList.);
        }
        return imageView;
    }
}

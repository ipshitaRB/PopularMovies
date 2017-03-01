package com.ipshita.androidprojects.popularmovies.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ipshita.androidprojects.popularmovies.R;
import com.ipshita.androidprojects.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom ArrayAdapter for movie grid view . shows thumbnails fetched using {@link Picasso}
 * Created by Ipshita on 18-02-2017.
 */

public class MovieThumbnailAdapter extends ArrayAdapter<Movie> {

    private static final int LAYOUT_PARAM_LENGTH = 500;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(LAYOUT_PARAM_LENGTH, LAYOUT_PARAM_LENGTH));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int paddingDimension = (int) getContext().getResources().getDimension(R.dimen.image_view_padding);
            imageView.setPadding(paddingDimension, paddingDimension, paddingDimension, paddingDimension);

        } else {
            imageView = (ImageView) convertView;
        }
        if (null != movieList && !movieList.isEmpty()) {
            Movie movie = movieList.get(position);
            if (null != movie.getPosterPath() && !movie.getPosterPath().isEmpty())
                Picasso.with(getContext())
                        .load("http://image.tmdb.org/t/p/w185/"
                                + movie.getPosterPath())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher).
                        into(imageView);

            else {
                imageView.setContentDescription(getContext().getString(R.string.no_poster_image_found));
            }
            //imageView.setImageResource(movieList.);

            // TODO: 19-02-2017 build url and use
            // done: 19-02-2017 handle when no poster path given
        }
        return imageView;
    }
}

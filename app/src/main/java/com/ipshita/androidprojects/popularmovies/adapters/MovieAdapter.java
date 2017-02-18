package com.ipshita.androidprojects.popularmovies.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ipshita.androidprojects.popularmovies.models.Movie;

/**
 * Created by Ipshita on 17-02-2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, int resource) {
        super(context, resource);
    }
}

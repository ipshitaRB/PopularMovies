package com.ipshita.androidprojects.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipshita.androidprojects.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    private static final String MOVIE_OBJECT_NOT_RECEIVED = "movie not received ";

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView synopsisTextView;
    private TextView ratingTextView;
    private TextView releaseDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        posterImageView = (ImageView) findViewById(R.id.movie_thumbnail_imageview);
        titleTextView = (TextView) findViewById(R.id.movie_title_textview);
        synopsisTextView = (TextView) findViewById(R.id.movie_synopsis_textview);
        ratingTextView = (TextView) findViewById(R.id.movie_rating_textview);
        releaseDateTextView = (TextView) findViewById(R.id.movie_release_date_textview);

        Intent intentThatCalledThisActivity = getIntent();
        Context context = getApplicationContext();
        if (intentThatCalledThisActivity.hasExtra(getString(R.string.movie_object_key))) {
            Movie clickedMovie = intentThatCalledThisActivity.getParcelableExtra(getString(R.string.movie_object_key));
            if (null != clickedMovie.getPosterPath() && !clickedMovie.getPosterPath().isEmpty())

                // TODO: 19-02-2017 build url and use
                // TODO: 19-02-2017 handle when no poster path given
                Picasso.with(context).load("http://image.tmdb.org/t/p/original/"
                        + clickedMovie.getPosterPath()).into(posterImageView);
            titleTextView.setText(clickedMovie.getTitle());
            synopsisTextView.setText(clickedMovie.getSynopsis());
            ratingTextView.setText(String.valueOf(clickedMovie.getAverageVote()));
            releaseDateTextView.setText(clickedMovie.getReleaseDate());

        } else {
            Log.v(TAG, MOVIE_OBJECT_NOT_RECEIVED);
        }
    }
}

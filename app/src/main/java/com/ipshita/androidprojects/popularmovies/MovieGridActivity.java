package com.ipshita.androidprojects.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ipshita.androidprojects.popularmovies.utilities.NetworkUtils;
import com.ipshita.androidprojects.popularmovies.utilities.SortBy;

import java.io.IOException;
import java.net.URL;

/**
 * This class shows movies in a grid layout and arranges them in the order of most popular or top rated.
 * The movie thumbnails are shown using Picasso and MovieDB API
 * @author Ipshita on 02-17-2017
 */
public class MovieGridActivity extends AppCompatActivity {

    // TODO: 17-02-2017 remove the textview after adding list
    private TextView temporaryMovieDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        temporaryMovieDataTextView = (TextView) findViewById(R.id.tv_movie_data);

        loadMovieData();

    }

    private void loadMovieData() {
        // completed: 17-02-2017 call FetchMovieTask.execute()
        new FetchMovieTask().execute(SortBy.POPULARITY);
    }

    // TODO: 17-02-2017 Create FetchMovieTask,, which extends AsyncTask to fetch movie data from moviedb api
    public class FetchMovieTask extends AsyncTask<SortBy, Void, String> {


        @Override
        protected String doInBackground(SortBy... sortBies) {
            // if there is no sortBy preference then the movies can't be looked up
            if (sortBies.length == 0) {
                return null;
            }
            SortBy sortByPreference = sortBies[0];

            URL moviesRequestUrl = NetworkUtils.buildDiscoverMoviesBySortUrl(sortByPreference);

            String moviesJSONResponse = null;
            try {
                moviesJSONResponse = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //String[] moviesData = MovieJsonUtils.getSimpleMovieStringsFromJson(MovieGridActivity.this, moviesJSONResponse);

            return moviesJSONResponse;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            temporaryMovieDataTextView.setText(s);
        }
    }


    // TODO: 17-02-2017 check internet connection
    // completed: 17-02-2017 in doinbackground append it to the textview

    // TODO: 17-02-2017 show progressbar in case intenet is slow
    


}

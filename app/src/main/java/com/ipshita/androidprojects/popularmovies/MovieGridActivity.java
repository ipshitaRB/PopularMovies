package com.ipshita.androidprojects.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ipshita.androidprojects.popularmovies.models.Movie;
import com.ipshita.androidprojects.popularmovies.utilities.MovieJsonUtils;
import com.ipshita.androidprojects.popularmovies.utilities.NetworkUtils;
import com.ipshita.androidprojects.popularmovies.utilities.SortBy;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * This class shows movies in a grid layout and arranges them in the order of most popular or top rated.
 * The movie thumbnails are shown using Picasso and MovieDB API
 * @author Ipshita on 02-17-2017
 */
public class MovieGridActivity extends AppCompatActivity {

    private static final String TAG = MovieGridActivity.class.getSimpleName();
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
    public class FetchMovieTask extends AsyncTask<SortBy, Void, List<Movie>> {


        @Override
        protected List<Movie> doInBackground(SortBy... sortBies) {
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

            List<Movie> movieList = MovieJsonUtils.getMoviesFromJson(moviesJSONResponse);

            return movieList;

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            if (movies == null) {
                Log.v(TAG, "movie list is null");
            } else if (movies.isEmpty()) {
                Log.v(TAG, "movie list is empty");

            } else {

                for (int i = 0; i < movies.size(); i++) {
                    temporaryMovieDataTextView.append(movies.get(i).getTitle() + "\n\n");
                }
            }
        }
    }


    // TODO: 17-02-2017 check internet connection
    // completed: 17-02-2017 in doinbackground append it to the textview

    // TODO: 17-02-2017 show progressbar in case intenet is slow
    


}

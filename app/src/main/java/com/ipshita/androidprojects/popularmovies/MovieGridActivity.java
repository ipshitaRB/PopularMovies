package com.ipshita.androidprojects.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ipshita.androidprojects.popularmovies.adapters.MovieThumbnailAdapter;
import com.ipshita.androidprojects.popularmovies.models.Movie;
import com.ipshita.androidprojects.popularmovies.utilities.MovieJsonUtils;
import com.ipshita.androidprojects.popularmovies.utilities.NetworkUtils;
import com.ipshita.androidprojects.popularmovies.utilities.SortBy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class shows movies in a grid layout and arranges them in the order of most popular or top rated.
 * The movie thumbnails are shown using Picasso and MovieDB API
 * @author Ipshita on 02-17-2017
 */
public class MovieGridActivity extends AppCompatActivity {

    private static final String TAG = MovieGridActivity.class.getSimpleName();

    private ProgressBar loadMoviesProgressBar;

    private GridView movieGridView;

    private List<Movie> movieList;

    private MovieThumbnailAdapter movieAdapter;

    private Spinner sortBySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        loadMoviesProgressBar = (ProgressBar) findViewById(R.id.pb_load_movie);

        movieGridView = (GridView) findViewById(R.id.movie_grid_view);

        final Context context = getApplicationContext();
        int resourceId = 0;
        movieList = new ArrayList<>();
        movieAdapter = new MovieThumbnailAdapter(context, resourceId, movieList);
        movieGridView.setAdapter(movieAdapter);
        movieGridView.setNumColumns(2);
        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Movie clickedMovie = movieAdapter.getItem(position);
                Intent intentToStartMovieDetailActivity = new Intent(context, MovieDetailActivity.class);
                intentToStartMovieDetailActivity.putExtra(getString(R.string.movie_object_key), clickedMovie);
                startActivity(intentToStartMovieDetailActivity);
            }
        });


        if (NetworkUtils.isNetworkAvailable(context)) {

            loadMovieData(SortBy.POPULARITY);
        } else {
            Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

        /*sortBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sortBySpinner.getSelectedItemPosition() == 0) {
                    if (NetworkUtils.isNetworkAvailable(context)) {

                        loadMovieData(SortBy.POPULARITY);
                    } else {
                        Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (NetworkUtils.isNetworkAvailable(context)) {

                        loadMovieData(SortBy.RATING);
                    } else {
                        Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        




    }


    private void loadMovieData(SortBy sortPreference) {
        // completed: 17-02-2017 call FetchMovieTask.execute()
        new FetchMovieTask().execute(sortPreference);
    }

    // TODO: 17-02-2017 Create FetchMovieTask,, which extends AsyncTask to fetch movie data from moviedb api
    public class FetchMovieTask extends AsyncTask<SortBy, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadMoviesProgressBar.setVisibility(View.VISIBLE);
        }

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


            return MovieJsonUtils.getMoviesFromJson(moviesJSONResponse);

        }

        // TODO: 17-02-2017 check internet connection
// TODO: 17-02-2017 show progressbar in case intenet is slow
        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            loadMoviesProgressBar.setVisibility(View.INVISIBLE);
            if (movies == null) {
                Log.v(TAG, "movie list is null");
                // completed: 19-02-2017 no result found
                Toast.makeText(MovieGridActivity.this, getString(R.string.no_result_found), Toast.LENGTH_LONG).show();
            } else if (movies.isEmpty()) {
                Log.v(TAG, "movie list is empty");
                Toast.makeText(MovieGridActivity.this, getString(R.string.no_result_found), Toast.LENGTH_LONG).show();
                // completed: 19-02-2017 no result found
            } else {
                movieList.clear();
                movieList.addAll(movies);
                movieAdapter.addAll(movieList);


                // completed: 19-02-2017 do something about the new itemclicklisteners every time data is loaded :|

            }
        }


    }


    // completed: 17-02-2017 in doinbackground append it to the textview


}

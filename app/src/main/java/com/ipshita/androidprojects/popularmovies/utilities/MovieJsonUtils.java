package com.ipshita.androidprojects.popularmovies.utilities;

import com.ipshita.androidprojects.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link MovieJsonUtils} is serializing JSON response into {@link com.ipshita.androidprojects.popularmovies.models.Movie}
 * obJects
 * Created by Ipshita on 18-02-2017.
 */
public class MovieJsonUtils {

    // TODO: 18-02-2017 convert json to Movie objects

    /*"poster_path": "/9Hj2bqi955SvTa5zj7uZs6sic29.jpg",
            "adult": false,
            "overview": "",
            "release_date": "2015-03-15",
            "genre_ids": [
            99
            ],
            "id": 441580,
            "original_title": "The Jinx: The Life and Deaths of Robert Durst Season 1 Chapter 6: What the Hell Did I Do?",
            "original_language": "en",
            "title": "The Jinx: The Life and Deaths of Robert Durst Season 1 Chapter 6: What the Hell Did I Do?",
            "backdrop_path": "/3br0Rt90AkaqiwVBZVvVUYD1juQ.jpg",
            "popularity": 1,
            "vote_count": 1,
            "video": false,
            "vote_average": 10*/
    private final static String RESULTS_JSON_ARRAY_KEY = "results";
    private final static String POSTER_PATH_KEY = "poster_path";
    private final static String SYNOPSIS_KEY = "overview";
    private final static String RELEASE_DATE_KEY = "release_date";
    private final static String ID_KEY = "id";
    private final static String VOTE_KEY = "vote_average";
    private final static String TITLE_KEY = "title";


    public static List<Movie> getMoviesFromJson(String moviesJSONResponse) {

        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        JSONObject movieJsonObject = null;
        int id;
        Double averageVote;
        String title;
        String releaseDate;
        String synopsis;
        String posterPath;
        Movie.Builder movieBuilder;


        try {
            JSONObject pageRootObject = new JSONObject(moviesJSONResponse);
            JSONArray resultsJsonArray = pageRootObject.getJSONArray(RESULTS_JSON_ARRAY_KEY);

            for (int i = 0; i < resultsJsonArray.length(); i++) {
                movieJsonObject = resultsJsonArray.getJSONObject(i);
                id = movieJsonObject.getInt(ID_KEY);
                averageVote = movieJsonObject.getDouble(VOTE_KEY);
                posterPath = movieJsonObject.getString(POSTER_PATH_KEY);
                title = movieJsonObject.getString(TITLE_KEY);
                synopsis = movieJsonObject.getString(SYNOPSIS_KEY);
                releaseDate = movieJsonObject.getString(RELEASE_DATE_KEY);

                movieBuilder = new Movie.Builder()
                        .addId(id)
                        .addAverageVote(averageVote)
                        .addTitle(title)
                        .addPosterPath(posterPath)
                        .addSynopsis(synopsis)
                        .addReleaseDate(releaseDate);
                movie = movieBuilder.build();

                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }
}

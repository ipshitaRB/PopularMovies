package com.ipshita.androidprojects.popularmovies.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * It simplifies Network connectivity from {@link com.ipshita.androidprojects.popularmovies.MovieGridActivity}
 * Created by Ipshita on 18-02-2017.
 */

public final class NetworkUtils {


    private final static String API_KEY_PARAM = "api_key";
    private final static String SORT_BY_PARAM = "sort_by";


    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String MOVIE_URL =
            "https://api.themoviedb.org/3/discover/movie";


    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param sortByQuery The location that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildDiscoverMoviesBySortUrl(SortBy sortByQuery) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Weather Map's API
        Uri builtUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, com.ipshita.androidprojects.popularmovies.BuildConfig.MOVIE_DB_API_KEY)
                .appendQueryParameter(SORT_BY_PARAM, sortByQuery.toString())
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

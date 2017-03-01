package com.ipshita.androidprojects.popularmovies.utilities;

/**
 * Enum SortBy is created to offer 2 ways to sort movies - popularity or rating
 * It is used in {@link NetworkUtils} build url method
 * Created by Ipshita on 18-02-2017.
 */
// TODO: 01-03-2017 enum costly. needs to be replaced 
public enum SortBy {

    POPULARITY("popular"),
    RATING("top_rated");


    private final String value;

    SortBy(String value) {
        this.value = value;
    }

    public static SortBy whichSortBy(String value) {
        if (value.equals(POPULARITY.getValue()))
            return POPULARITY;
        else if (value.equals(RATING.getValue()))
            return RATING;
        else
            return null;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}

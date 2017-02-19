package com.ipshita.androidprojects.popularmovies.utilities;

/**
 * Enum SortBy is created to offer 2 ways to sort movies - popularity or rating
 * It is used in {@link NetworkUtils} build url method
 * Created by Ipshita on 18-02-2017.
 */

public enum SortBy {

    POPULARITY("popularity.desc"),
    RATING("vote_average.desc");


    private final String value;

    SortBy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}

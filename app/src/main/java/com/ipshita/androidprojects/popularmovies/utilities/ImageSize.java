package com.ipshita.androidprojects.popularmovies.utilities;

/**
 * TO be used for build Picasso image url
 * Created by Ipshita on 20-02-2017.
 */
public enum ImageSize {


    W92("w92/"),
    W154("w154/"),
    W185("w185'/'/"),
    W342("w342/"),
    W500("w500/"),
    W780("w780/"),
    ORIGINAL("original/");


    private final String value;

    ImageSize(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}

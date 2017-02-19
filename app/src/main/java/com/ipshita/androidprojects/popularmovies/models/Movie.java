package com.ipshita.androidprojects.popularmovies.models;

/**
 * Model class for movies
 * Movie details layout contains title, release date, movie poster, vote average, and plot synopsis.
 * Uses Builder pattern to populate fields
 * Created by Ipshita on 17-02-2017.
 */

public class Movie {

    // TODO: 18-02-2017 make it parcelable later 

    private final int id;
    private final String title;
    private final String posterPath;
    private final Double averageVote;
    private final String synopsis;
    private final String releaseDate;

    public Movie(Builder builder) {

        this.id = builder.id;
        this.title = builder.title;
        this.averageVote = builder.averageVote;
        this.posterPath = builder.posterPath;
        this.releaseDate = builder.releaseDate;
        this.synopsis = builder.synopsis;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Double getAverageVote() {
        return averageVote;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static class Builder {

        private int id;
        private String title;
        private String posterPath;
        private Double averageVote;
        private String synopsis;
        private String releaseDate;


        public Builder addTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder addId(int id) {
            this.id = id;
            return this;
        }

        public Builder addPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Builder addAverageVote(Double averageVote) {
            this.averageVote = averageVote;
            return this;
        }

        public Builder addSynopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public Builder addReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }



}

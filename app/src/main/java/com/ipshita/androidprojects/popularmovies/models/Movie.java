package com.ipshita.androidprojects.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for movies
 * Movie details layout contains title, release date, movie poster, vote average, and plot synopsis.
 * Uses Builder pattern to populate fields
 * {@link Movie} implements {@link Parcelable } so that it's instance can be passed from one
 * activity to another.
 * Created by Ipshita on 17-02-2017.
 */

public class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // completed: 18-02-2017 make it parcelable later

    private final int id;
    private final String title;
    private final String posterPath;
    private final Double averageVote;
    private final String synopsis;
    private final String releaseDate;

    private Movie(Builder builder) {

        this.id = builder.id;
        this.title = builder.title;
        this.averageVote = builder.averageVote;
        this.posterPath = builder.posterPath;
        this.releaseDate = builder.releaseDate;
        this.synopsis = builder.synopsis;
    }

    private Movie(Parcel movieParcel) {
        this.id = movieParcel.readInt();
        this.title = movieParcel.readString();
        this.posterPath = movieParcel.readString();
        this.averageVote = movieParcel.readDouble();
        this.synopsis = movieParcel.readString();
        this.releaseDate = movieParcel.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destinationParcel, int i) {
        destinationParcel.writeInt(id);
        destinationParcel.writeString(title);
        destinationParcel.writeString(posterPath);
        destinationParcel.writeDouble(averageVote);
        destinationParcel.writeString(synopsis);
        destinationParcel.writeString(releaseDate);

    }

    @Override
    public String toString() {

        String movieDetails = "ID : " + this.getId() + "\n" +
                "Title : " + this.getTitle() + "\n" +
                "PosterPath : " + this.getPosterPath() + "\n"
                + "averageVote : " + this.getAverageVote() + "\n"
                + "synopsis : " + this.getSynopsis() + "\n"
                + "release Date : " + this.getReleaseDate();


        return movieDetails;
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

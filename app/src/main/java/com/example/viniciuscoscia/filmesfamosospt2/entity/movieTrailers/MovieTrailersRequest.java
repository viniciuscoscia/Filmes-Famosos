package com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailersRequest implements Parcelable {
    private long id;
    @SerializedName("results")
    private List<MovieTrailer> movieTrailerList;

    protected MovieTrailersRequest(Parcel in) {
        id = in.readLong();
    }

    public static final Creator<MovieTrailersRequest> CREATOR = new Creator<MovieTrailersRequest>() {
        @Override
        public MovieTrailersRequest createFromParcel(Parcel in) {
            return new MovieTrailersRequest(in);
        }

        @Override
        public MovieTrailersRequest[] newArray(int size) {
            return new MovieTrailersRequest[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MovieTrailer> getMovieTrailerList() {
        return movieTrailerList;
    }

    public void setMovieTrailerList(List<MovieTrailer> movieTrailerList) {
        this.movieTrailerList = movieTrailerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }
}

package com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieTrailer implements Parcelable {

    private String id;
    @SerializedName("iso_639_1")
    private String iso6391;
    @SerializedName("iso_3166_1")
    private String iso_31661;
    private String key;
    private String name;
    private String site;
    private long size;
    private String type;

    protected MovieTrailer(Parcel in) {
        id = in.readString();
        iso6391 = in.readString();
        iso_31661 = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        size = in.readLong();
        type = in.readString();
    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso_31661() {
        return iso_31661;
    }

    public void setIso_31661(String iso_31661) {
        this.iso_31661 = iso_31661;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(iso6391);
        dest.writeString(iso_31661);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeLong(size);
        dest.writeString(type);
    }
}

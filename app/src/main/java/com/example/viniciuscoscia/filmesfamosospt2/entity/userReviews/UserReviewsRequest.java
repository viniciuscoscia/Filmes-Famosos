package com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserReviewsRequest implements Parcelable {
    private long id;
    private long page;
    @SerializedName("results")
    private List<UserReview> userReviewList;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    protected UserReviewsRequest(Parcel in) {
        id = in.readLong();
        page = in.readLong();
        userReviewList = in.createTypedArrayList(UserReview.CREATOR);
        totalPages = in.readInt();
        totalResults = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(page);
        dest.writeTypedList(userReviewList);
        dest.writeInt(totalPages);
        dest.writeInt(totalResults);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserReviewsRequest> CREATOR = new Creator<UserReviewsRequest>() {
        @Override
        public UserReviewsRequest createFromParcel(Parcel in) {
            return new UserReviewsRequest(in);
        }

        @Override
        public UserReviewsRequest[] newArray(int size) {
            return new UserReviewsRequest[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<UserReview> getUserReviewList() {
        return userReviewList;
    }

    public void setUserReviewList(List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
    }

    public UserReviewsRequest() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public static Creator<UserReviewsRequest> getCREATOR() {
        return CREATOR;
    }
}

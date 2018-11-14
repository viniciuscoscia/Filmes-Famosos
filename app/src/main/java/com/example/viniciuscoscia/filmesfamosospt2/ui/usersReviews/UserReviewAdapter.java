package com.example.viniciuscoscia.filmesfamosospt2.ui.usersReviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews.UserReview;

import java.util.List;

public class UserReviewAdapter extends RecyclerView.Adapter<UserReviewAdapter.UserReviewViewHolder> {

    private List<UserReview> userReviewList;

    public void setUserReviewList(List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_review_card, viewGroup, false);

        return new UserReviewAdapter.UserReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserReviewViewHolder userReviewViewHolder, int i) {
        UserReview userReview = userReviewList.get(i);
        userReviewViewHolder.userName.setText(userReview.getAuthor());
        userReviewViewHolder.userReview.setText(userReview.getContent());
    }

    @Override
    public int getItemCount() {
        return userReviewList.size();
    }

    static class UserReviewViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView userReview;

        public UserReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_user);
            userReview = itemView.findViewById(R.id.tv_userReview);
        }
    }
}

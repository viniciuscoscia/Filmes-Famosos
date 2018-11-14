package com.example.viniciuscoscia.filmesfamosospt2.ui.usersReviews;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews.UserReviewsRequest;
import com.example.viniciuscoscia.filmesfamosospt2.ui.movieDetails.MovieDetailsViewModel;

public class UsersReviewsActivity extends AppCompatActivity {

    public static final String REVIEWS_KEY = "reviews_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_reviews);

        UsersReviewsViewModel usersReviewsViewModel = ViewModelProviders.of(this).get(UsersReviewsViewModel.class);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        Intent intent = getIntent();
        UserReviewsRequest userReviewsRequest = intent.getParcelableExtra(REVIEWS_KEY);

        UserReviewAdapter adapter = new UserReviewAdapter();
        adapter.setUserReviewList(userReviewsRequest.getUserReviewList());

        RecyclerView recyclerView = findViewById(R.id.rv_users_reviews);
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}

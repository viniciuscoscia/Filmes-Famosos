package com.example.viniciuscoscia.filmesfamosospt2.ui.movieDetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.databinding.MovieDetailsBinding;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.ui.FilmesFamososActivity;
import com.example.viniciuscoscia.filmesfamosospt2.ui.movieTrailers.MovieTrailersActivity;
import com.example.viniciuscoscia.filmesfamosospt2.ui.usersReviews.UsersReviewsActivity;

public class MovieDetailsActivity extends FilmesFamososActivity {

    private MovieDetailsViewModel movieDetailsViewModel;
    private MovieDetailsBinding binding;
    private ImageView favoriteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.movie_details);
        setErrorMessageView(findViewById(android.R.id.content));

        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        configActivity();
    }

    private void configActivity() {
        setLayoutMovie();
        movieDetailsViewModel.getMovieUsersReviews(this);
        movieDetailsViewModel.getMovieTrailers(this);
        movieDetailsViewModel.getIsMovieFavorite().observe(this, isFavorite -> {
            if(isFavorite) {
                favoriteIcon.setImageResource(R.drawable.ic_favorite_white_full_24dp);
            } else {
                favoriteIcon.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            }
        });
    }

    private void setupActionBar() {
        getSupportActionBar().hide();
    }

    private void setLayoutMovie() {
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(Movie.PARCELABLE_KEY);
        binding = DataBindingUtil.setContentView(this, R.layout.movie_details);
        binding.setMovie(movie);
        movieDetailsViewModel.setMovie(movie);
        favoriteIcon = findViewById(R.id.iv_favorite);
    }

    private void configUsersReviewsActivityIntent() {
        CardView cardView = findViewById(R.id.cv_avaliacoes_usuarios);

        cardView.setOnClickListener(view -> {
            if(!hasInternetConnection()) {
                return;
            }
            Intent intent = new Intent(this, UsersReviewsActivity.class);
            intent.putExtra(UsersReviewsActivity.REVIEWS_KEY, movieDetailsViewModel.getUserReviewsRequest());
            startActivity(intent);
        });
    }

    public void setUsersReviewsNumber(long results) {
        binding.setReviewsNumber(String.valueOf(results));
        if (results > 0) {
            configUsersReviewsActivityIntent();
        }
    }

    public void setTrailersNumber(int size) {
        binding.setTrailersNumber(String.valueOf(size));
        if (size > 0) {
            configTrailersActivityIntent();
        }
    }

    private void configTrailersActivityIntent() {
        CardView cardView = findViewById(R.id.cv_trailers);

        cardView.setOnClickListener(view -> {
            if(!hasInternetConnection()) {
                return;
            }
            Intent intent = new Intent(this, MovieTrailersActivity.class);
            intent.putExtra(Movie.PARCELABLE_KEY, movieDetailsViewModel.getMovie());
            startActivity(intent);
        });
    }

    public void favoriteMovie(View view) {
        movieDetailsViewModel.favoriteUnfavoriteMovie();
    }

}

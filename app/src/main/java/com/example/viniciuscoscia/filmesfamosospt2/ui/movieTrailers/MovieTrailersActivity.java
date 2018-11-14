package com.example.viniciuscoscia.filmesfamosospt2.ui.movieTrailers;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.ui.FilmesFamososActivity;

public class MovieTrailersActivity extends FilmesFamososActivity implements MovieTrailersAdapter.MovieTrailerClickHandler {

    RecyclerView recyclerView;
    MovieTrailersViewModel movieTrailersViewModel;
    MovieTrailersAdapter movieTrailersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailers);
        setErrorMessageView(R.id.movie_trailers_layout);

        movieTrailersViewModel = ViewModelProviders.of(this).get(MovieTrailersViewModel.class);
        setupViewModel();
        setupRecyclerView();
    }

    private void setupViewModel() {
        Intent intent = getIntent();
        movieTrailersViewModel.setMovie(intent.getParcelableExtra(Movie.PARCELABLE_KEY));
        movieTrailersViewModel.getMovieTrailers(movieTrailersViewModel.getMovie().getId());
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_trailers);

        movieTrailersAdapter = new MovieTrailersAdapter(this);
        recyclerView.setAdapter(movieTrailersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        movieTrailersViewModel.getMovieTrailersRequestMutableLiveData().observe(
                this, movieTrailersRequest ->
                    movieTrailersAdapter.setMovieTrailerList(movieTrailersRequest.getMovieTrailerList())

        );
    }

    @Override
    public void onClick(String videoId) {
        if(!hasInternetConnection()) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId)));
    }

    @Override
    protected void noInternetConnectionAction() {

    }
}

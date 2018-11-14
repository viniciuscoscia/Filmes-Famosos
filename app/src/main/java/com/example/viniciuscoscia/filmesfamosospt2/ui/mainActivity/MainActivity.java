package com.example.viniciuscoscia.filmesfamosospt2.ui.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.ui.FilmesFamososActivity;
import com.example.viniciuscoscia.filmesfamosospt2.ui.movieDetails.MovieDetailsActivity;

public class MainActivity extends FilmesFamososActivity implements MovieAdapter.FilmeOnClickHandler {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;
    private TextView tvEmptyFavoritesMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setErrorMessageView(findViewById(R.id.main_activity));
        tvEmptyFavoritesMessage = findViewById(R.id.tv_no_favorites);

        progressBar = findViewById(R.id.main_activity_progress_bar);

        configureRecyclerView();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        setupBottonNavigationView();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setupMainViewModel();
    }

    private void setupMainViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        showLoading(true);
        if(hasInternetConnection()){
            searchMovies();
        }
    }

    private void configureRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView = findViewById(R.id.rv_movies);
        adapter = new MovieAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupBottonNavigationView() {
        mOnNavigationItemSelectedListener = item -> {
            mainViewModel.setLastSearchId(item.getItemId());
            if(hasInternetConnection()){
                return searchMovies();
            }
            return false;
        };
    }

    private boolean searchMovies() {
        showLoading(true);
        switch (mainViewModel.getLastSearchId()) {
            case R.id.filmes_populares:
                mainViewModel.getPopularMovies();
                showEmptyFavoritesMessage(false);
                break;
            case R.id.filmes_melhores_notas:
                mainViewModel.getTopRatedMovies();
                showEmptyFavoritesMessage(false);
                break;
            case R.id.filmes_favoritos:
                mainViewModel.getFavoriteMoviesFromDatabase(this);
                break;
            default:
                return false;
        }
        checkObserver();
        return true;
    }

    @Override
    public void onClick(Movie selectedMovie) {
        if(!hasInternetConnection()) {
            return;
        }
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Movie.PARCELABLE_KEY, selectedMovie);
        startActivity(intent);
    }

    private void showLoading(boolean loading) {
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(!loading ? View.GONE : View.VISIBLE);
    }

    private void checkObserver() {
        if(!mainViewModel.getMoviesRequest().hasObservers()){
            mainViewModel.getMoviesRequest().observe(this, movieRequest -> {
                adapter.setMovieRequest(movieRequest);
                showLoading(false);
            });
        }

        if(!mainViewModel.getFilmesFavoritos().hasObservers()) {
            mainViewModel.getFilmesFavoritos().observe(this, movieList -> {
                showEmptyFavoritesMessage(movieList.size() == 0);
                adapter.setMovieList(movieList);
                showLoading(false);
            });
        }
    }

    private void showEmptyFavoritesMessage(boolean isFavoritesEmpty) {
        recyclerView.setVisibility(isFavoritesEmpty ? View.GONE : View.VISIBLE);
        tvEmptyFavoritesMessage.setVisibility(isFavoritesEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void noInternetConnectionAction() {
        if(!hasInternetConnection()) {
            return;
        }
        searchMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchMovies();
    }
}

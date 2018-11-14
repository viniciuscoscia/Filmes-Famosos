package com.example.viniciuscoscia.filmesfamosospt2.ui.mainActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.database.AppDataBase;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.MoviesRequest;
import com.example.viniciuscoscia.filmesfamosospt2.moviesAPI.MoviesDAO;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<MoviesRequest> movieRequest = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> filmesFavoritos = new MutableLiveData<>();
    private int lastSearchId = R.id.filmes_populares;
    private MoviesDAO moviesDAO;

    public MainViewModel(Application application) {
        super(application);
        moviesDAO = new MoviesDAO();
    }

    public void getPopularMovies() {
        movieRequest = moviesDAO.getPopularMovies();
    }

    public void getTopRatedMovies() {
        movieRequest = moviesDAO.getTopRatedMovies();
    }

    public LiveData<MoviesRequest> getMoviesRequest() {
        return movieRequest;
    }

    public MutableLiveData<List<Movie>> getFilmesFavoritos() {
        return filmesFavoritos;
    }

    public void getFavoriteMoviesFromDatabase(LifecycleOwner lifecycleOwner) {
        AppDataBase.getInstance(getApplication())
                .favoritesDao()

                .loadAllFavoriteMovies()
                .observe(lifecycleOwner, result -> {
                    filmesFavoritos.setValue(result);
                });
    }

    public int getLastSearchId() {
        return lastSearchId;
    }

    public void setLastSearchId(int lastSearchId) {
        this.lastSearchId = lastSearchId;
    }
}

package com.example.viniciuscoscia.filmesfamosospt2.ui.movieTrailers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers.MovieTrailersRequest;
import com.example.viniciuscoscia.filmesfamosospt2.moviesAPI.MoviesDAO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieTrailersViewModel extends AndroidViewModel {

    private MoviesDAO moviesDAO = new MoviesDAO();
    private MutableLiveData<MovieTrailersRequest> movieTrailersRequestMutableLiveData = new MutableLiveData<>();
    private Movie movie;

    public MovieTrailersViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovieTrailers(long id) {
        moviesDAO.getMovieTrailers(id).enqueue(new Callback<MovieTrailersRequest>() {
            @Override
            public void onResponse(Call<MovieTrailersRequest> call, Response<MovieTrailersRequest> response) {
                if(response != null) {
                    movieTrailersRequestMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieTrailersRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<MovieTrailersRequest> getMovieTrailersRequestMutableLiveData() {
        return movieTrailersRequestMutableLiveData;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

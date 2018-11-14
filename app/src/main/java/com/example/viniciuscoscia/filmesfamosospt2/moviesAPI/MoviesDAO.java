package com.example.viniciuscoscia.filmesfamosospt2.moviesAPI;

import android.arch.lifecycle.MutableLiveData;

import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.MoviesRequest;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers.MovieTrailersRequest;
import com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews.UserReviewsRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDAO {

    IMoviesAPI moviesAPI;
    MutableLiveData<MoviesRequest> movieRequestMutableLiveData = new MutableLiveData<>();

    public MoviesDAO(){
        moviesAPI = MoviesRepository.getRetrofitInstance().create(IMoviesAPI.class);
    }

    public MutableLiveData<MoviesRequest> getPopularMovies(){
        moviesAPI
            .getPopularMovies()
            .enqueue(new Callback<MoviesRequest>() {
                @Override
                public void onResponse(Call<MoviesRequest> call, final Response<MoviesRequest> response) {
                    movieRequestMutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<MoviesRequest> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        return movieRequestMutableLiveData;
    }

    public MutableLiveData<MoviesRequest> getTopRatedMovies(){
        moviesAPI
                .getTopRatedMovies()
                .enqueue(new Callback<MoviesRequest>() {
                    @Override
                    public void onResponse(Call<MoviesRequest> call, final Response<MoviesRequest> response) {
                        movieRequestMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<MoviesRequest> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        return movieRequestMutableLiveData;
    }

    public Call<UserReviewsRequest> getUserReviewsForMovie(long movieId) {
        return moviesAPI
                .getUserReviewsForMovie(movieId);
    }

    public Call<MovieTrailersRequest> getMovieTrailers(long movieId) {
        return moviesAPI.getMovieTrailers(movieId);
    }
}

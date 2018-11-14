package com.example.viniciuscoscia.filmesfamosospt2.ui.movieDetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.example.viniciuscoscia.filmesfamosospt2.database.AppDataBase;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers.MovieTrailersRequest;
import com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews.UserReviewsRequest;
import com.example.viniciuscoscia.filmesfamosospt2.moviesAPI.MoviesDAO;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MutableLiveData<UserReviewsRequest> userReviewsRequest = new MutableLiveData<>();
    private MutableLiveData<MovieTrailersRequest> movieTrailersRequestMutableLiveData
                                                                         = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMovieFavorite = new MutableLiveData<>();
    private Movie movie = new Movie();
    private MoviesDAO moviesDAO = new MoviesDAO();
    private AppDataBase appDataBase = AppDataBase.getInstance(getApplication());

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovieUsersReviews(MovieDetailsActivity movieDetailsActivity) {
        moviesDAO.getUserReviewsForMovie(movie.getId()).enqueue(new Callback<UserReviewsRequest>() {
            @Override
            public void onResponse(Call<UserReviewsRequest> call, Response<UserReviewsRequest> response) {
                userReviewsRequest.setValue(response.body());
                movieDetailsActivity.setUsersReviewsNumber(response.body().getTotalResults());
            }

            @Override
            public void onFailure(Call<UserReviewsRequest> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getMovieTrailers(MovieDetailsActivity movieDetailsActivity) {
        moviesDAO.getMovieTrailers(movie.getId())
                 .enqueue(new Callback<MovieTrailersRequest>() {
            @Override
            public void onResponse(Call<MovieTrailersRequest> call, Response<MovieTrailersRequest> response) {
                if(response != null) {
                    movieTrailersRequestMutableLiveData.setValue(response.body());
                    movieDetailsActivity.setTrailersNumber(response.body().getMovieTrailerList().size());
                }
            }

            @Override
            public void onFailure(Call<MovieTrailersRequest> call, Throwable t) {
                Log.e("ERRO", t.getMessage());
            }
        });
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load("http://image.tmdb.org/t/p/w500//" + imageUrl)
                .into(view);
    }

    public void checkMovieFavorite() {
        AppDataBase appDataBase = AppDataBase.getInstance(getApplication());

        new AsyncTask<Movie, Movie, Boolean>() {
            @Override
            protected Boolean doInBackground(Movie... movies) {
                if(appDataBase.favoritesDao().loadFavoriteMovieById(movie.getId()) == null)
                    return false;
                else
                    return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                isMovieFavorite.setValue(result);
            }
        }.execute();
    }

    public void favoriteUnfavoriteMovie() {
        if(isMovieFavorite.getValue()) {
            unfavoriteMovie();
            return;
        }
        favoriteMovie();
    }

    private void unfavoriteMovie() {
        new AsyncTask<Movie, Movie, Void>(){
            @Override
            protected Void doInBackground(Movie... movies) {
                appDataBase.favoritesDao().deleteFavoriteMovie(movie);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                checkMovieFavorite();
            }
        }.execute();
    }

    private void favoriteMovie() {
        new AsyncTask<Movie, Movie, Void>(){
            @Override
            protected Void doInBackground(Movie... movies) {
                appDataBase.favoritesDao().insertFavoriteMovie(movie);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                checkMovieFavorite();
            }
        }.execute();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        checkMovieFavorite();
    }

    public UserReviewsRequest getUserReviewsRequest() {
        return userReviewsRequest.getValue();
    }

    public MutableLiveData<Boolean> getIsMovieFavorite() {
        return isMovieFavorite;
    }
}
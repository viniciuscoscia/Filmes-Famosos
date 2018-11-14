package com.example.viniciuscoscia.filmesfamosospt2.moviesAPI;

import com.example.viniciuscoscia.filmesfamosospt2.BuildConfig;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.MoviesRequest;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers.MovieTrailersRequest;
import com.example.viniciuscoscia.filmesfamosospt2.entity.userReviews.UserReviewsRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IMoviesAPI {

    String API_KEY_QUERY = "?api_key=" + BuildConfig.API_KEY;

    @GET("popular" + API_KEY_QUERY)
    Call<MoviesRequest> getPopularMovies();

    @GET("top_rated" + API_KEY_QUERY)
    Call<MoviesRequest> getTopRatedMovies();

    @GET("{movieId}/reviews" + API_KEY_QUERY)
    Call<UserReviewsRequest> getUserReviewsForMovie(@Path("movieId") long movieId);

    @GET("{movieId}/videos" + API_KEY_QUERY)
    Call<MovieTrailersRequest> getMovieTrailers(@Path("movieId") long movieId);
}

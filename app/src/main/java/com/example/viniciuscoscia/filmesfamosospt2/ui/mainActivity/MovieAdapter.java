package com.example.viniciuscoscia.filmesfamosospt2.ui.mainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.Movie;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movie.MoviesRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {

    private final FilmeOnClickHandler eventoClick;
    private List<Movie> movieList = new ArrayList<>();

    public void setMovieRequest(MoviesRequest moviesRequest) {
        movieList = moviesRequest.getResults();
        notifyDataSetChanged();
    }

    public void setMovieList(List<Movie> listaFilmes) {
        movieList = listaFilmes;
        notifyDataSetChanged();
    }

    public interface FilmeOnClickHandler {
        void onClick(Movie selectedMovie);
    }

    public MovieAdapter(FilmeOnClickHandler eventoClick) {
        this.eventoClick = eventoClick;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie, parent, false);

        return new MovieViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(holder.imgPoster.getContext()).load("http://image.tmdb.org/t/p/w500//" + movie.getPosterPath()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        if (null == movieList)
            return 0;
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie filmeSelecionado = movieList.get(getAdapterPosition());
            eventoClick.onClick(filmeSelecionado);
        }
    }
}

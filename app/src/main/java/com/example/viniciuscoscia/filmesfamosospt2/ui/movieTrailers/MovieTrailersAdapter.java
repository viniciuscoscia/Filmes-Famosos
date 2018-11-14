package com.example.viniciuscoscia.filmesfamosospt2.ui.movieTrailers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.entity.movieTrailers.MovieTrailer;

import java.util.ArrayList;
import java.util.List;

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MovieTrailersViewHolder> {

    private List<MovieTrailer> movieTrailerList = new ArrayList<>();
    private final MovieTrailerClickHandler movieTrailerClickHandler;

    public MovieTrailersAdapter(MovieTrailerClickHandler movieTrailerClickHandler) {
        this.movieTrailerClickHandler = movieTrailerClickHandler;
    }

    public interface MovieTrailerClickHandler {
        void onClick(String trailerName);
    }

    @NonNull
    @Override
    public MovieTrailersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trailer_card, viewGroup, false);

        return new MovieTrailersAdapter.MovieTrailersViewHolder(itemList);
    }

    @Override
    public int getItemCount() {
        return movieTrailerList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailersViewHolder movieTrailersViewHolder, int i) {
        movieTrailersViewHolder.trailerName.setText(movieTrailerList.get(i).getName());
    }

    public void setMovieTrailerList(List<MovieTrailer> movieTrailerList) {
        this.movieTrailerList = movieTrailerList;
        notifyDataSetChanged();
    }

    public class MovieTrailersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView trailerName;

        public MovieTrailersViewHolder(View itemView) {
            super(itemView);
            trailerName = itemView.findViewById(R.id.tv_trailer_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MovieTrailer filmeSelecionado = movieTrailerList.get(getAdapterPosition());
            movieTrailerClickHandler.onClick(filmeSelecionado.getKey());
        }
    }
}

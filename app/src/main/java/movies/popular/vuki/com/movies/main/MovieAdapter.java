package movies.popular.vuki.com.movies.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.databinding.ItemMovieBinding;
import movies.popular.vuki.com.movies.helpers.ImageHelper;

/**
 * Created by mvukosav
 */
class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> data;
    private MainActivityContract.Presenter presenter;

    MovieAdapter( List<Movie> data, MainActivityContract.Presenter presenter ) {
        this.data = data;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        final ItemMovieBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ),
                R.layout.item_movie, parent, false );

        final MovieViewHolder movieViewHolder = new MovieViewHolder( binding );

        binding.poster.setOnClickListener( view -> presenter.openMovieDetails( data.get( movieViewHolder.getAdapterPosition() ),
                movieViewHolder.getAdapterPosition() ) );

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder( @NonNull MovieViewHolder holder, int position ) {
        Movie movie = data.get( position );
        holder.binding.title.setText( movie.getOriginalTitle() );
        ImageHelper.getDrawableFromNetwork( holder.binding.poster, movie.getPosterThumbnail() );

        holder.binding.poster.setTransitionName( MainActivity.TRANSITION_POSTER_ITEM + position );
        holder.binding.title.setTransitionName( MainActivity.TRANSITION_TITLE_ITEM + position );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding binding;

        MovieViewHolder( ItemMovieBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }
    }

    public void setMovies( List<Movie> movies ) {
        data.clear();
        data.addAll( movies );
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public void addMovies( List<Movie> movies ) {
        data.addAll( movies );
        notifyDataSetChanged();
    }
}

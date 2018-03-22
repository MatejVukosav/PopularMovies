package movies.popular.vuki.com.popularmovies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import movies.popular.vuki.com.popularmovies.databinding.ItemMovieBinding;
import movies.popular.vuki.com.popularmovies.helpers.ImageHelper;
import movies.popular.vuki.com.popularmovies.models.Movie;

/**
 * Created by mvukosav
 */
class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> data;
    private MainActivityContract.Presenter presenter;

    public MovieAdapter( List<Movie> data, MainActivityContract.Presenter presenter ) {
        this.data = data;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        final ItemMovieBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ),
                R.layout.item_movie, parent, false );

        final MovieViewHolder movieViewHolder = new MovieViewHolder( binding );

        binding.poster.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                presenter.openMovieDetails( data.get( movieViewHolder.getAdapterPosition() ), movieViewHolder.getAdapterPosition() );
            }
        } );

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder( @NonNull MovieViewHolder holder, int position ) {
        Movie movie = data.get( position );
        holder.binding.title.setText( movie.getOriginalTitle() );
        ImageHelper.getDrawableFromNetwork( holder.binding.poster, movie.getPosterThumbnail() );

        holder.binding.poster.setTransitionName( "poster" + position );
        holder.binding.title.setTransitionName( "title" + position );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding binding;

        public MovieViewHolder( ItemMovieBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }
    }

    public void addMovies( List<Movie> movies ) {
        data.addAll( movies );
        notifyDataSetChanged();
    }
}

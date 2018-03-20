package movies.popular.vuki.com.popularmovies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import movies.popular.vuki.com.popularmovies.databinding.ItemMovieBinding;
import movies.popular.vuki.com.popularmovies.models.Movie;

/**
 * Created by mvukosav
 */
class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> data;

    public MovieAdapter( List<Movie> data ) {
        this.data = data;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        ItemMovieBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ),
                R.layout.item_movie, parent, false );
        return new MovieViewHolder( binding );
    }

    @Override
    public void onBindViewHolder( @NonNull MovieViewHolder holder, int position ) {
        holder.binding.image.setText( data.get( position ).getPosterThumbnail() );
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

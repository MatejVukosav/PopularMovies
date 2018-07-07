package movies.popular.vuki.com.movies.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.databinding.ModelTrailerBinding;
import movies.popular.vuki.com.movies.models.Trailer;

/**
 * Created by mvukosav
 */
public class TrailersRecyclerViewAdapter extends RecyclerView.Adapter<TrailersRecyclerViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick( int position );
    }

    private Context context;
    private List<Trailer> list;
    private OnItemClickListener onItemClickListener;

    public TrailersRecyclerViewAdapter( Context context, List<Trailer> list,
                                        OnItemClickListener onItemClickListener ) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        final ModelTrailerBinding binding = DataBindingUtil
                .inflate( LayoutInflater.from( parent.getContext() ),
                        R.layout.model_trailer, parent, false );
        return new ViewHolder( binding );
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        Trailer item = list.get( position );
        holder.bind( item, onItemClickListener );
        holder.binding.name.setText( item.getName() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ModelTrailerBinding binding;

        public ViewHolder( ModelTrailerBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }

        public void bind( final Trailer model,
                          final OnItemClickListener listener ) {
            itemView.setOnClickListener( v -> listener.onItemClick( getLayoutPosition() ) );
        }
    }

    public void set( List<Trailer> trailers ) {
        this.list = trailers;
        notifyDataSetChanged();
    }

}
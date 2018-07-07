package movies.popular.vuki.com.movies.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.databinding.ModelReviewBinding;
import movies.popular.vuki.com.movies.models.Review;

/**
 * Created by mvukosav
 */
public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick( int position );
    }

    private Context context;
    private List<Review> list;
    private OnItemClickListener onItemClickListener;

    public ReviewsRecyclerViewAdapter( Context context, List<Review> list,
                                       OnItemClickListener onItemClickListener ) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        final ModelReviewBinding binding = DataBindingUtil
                .inflate( LayoutInflater.from( parent.getContext() ),
                        R.layout.model_review, parent, false );
        return new ViewHolder( binding );
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        Review item = list.get( position );
        holder.bind( item, onItemClickListener );

        holder.binding.author.setText( item.getAuthor() );
        holder.binding.content.setText( item.getContent() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ModelReviewBinding binding;

        public ViewHolder( ModelReviewBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }

        public void bind( final Review model,
                          final OnItemClickListener listener ) {
            itemView.setOnClickListener( v -> listener.onItemClick( getLayoutPosition() ) );
        }
    }

    public void set( List<Review> reviews ) {
        this.list = reviews;
        notifyDataSetChanged();
    }

}
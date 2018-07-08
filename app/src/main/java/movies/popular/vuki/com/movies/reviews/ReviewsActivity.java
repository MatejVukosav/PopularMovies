package movies.popular.vuki.com.movies.reviews;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.adapters.ReviewsRecyclerViewAdapter;
import movies.popular.vuki.com.movies.databinding.ActivityReviewsBinding;
import movies.popular.vuki.com.movies.models.Review;

public class ReviewsActivity extends AppCompatActivity implements ReviewsRecyclerViewAdapter.OnItemClickListener {

    private ActivityReviewsBinding binding;
    private ReviewsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_reviews );

        setSupportActionBar( binding.toolbar );
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setDisplayShowHomeEnabled( true );
            actionBar.setTitle( "Reviews" );
        }

        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            List<Review> reviewList = b.getParcelableArrayList( "list" );

            adapter = new ReviewsRecyclerViewAdapter( this, reviewList, this );
            binding.recyclerView.setAdapter( adapter );
            binding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        }
    }

    @Override
    public void onItemClick( int position ) {

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == android.R.id.home ) {
            finish();
        }
        return super.onOptionsItemSelected( item );
    }

}

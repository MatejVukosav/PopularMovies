package movies.popular.vuki.com.movies.details;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.adapters.TrailersRecyclerViewAdapter;
import movies.popular.vuki.com.movies.databinding.ActivityMovieDetailsBinding;
import movies.popular.vuki.com.movies.databinding.ItemMovieDetailsBinding;
import movies.popular.vuki.com.movies.helpers.ImageHelper;
import movies.popular.vuki.com.movies.main.MainActivity;
import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.models.Review;
import movies.popular.vuki.com.movies.models.Trailer;
import movies.popular.vuki.com.movies.reviews.ReviewsActivity;

public class MovieDetails extends AppCompatActivity
        implements MovieDetailsContract.View, TrailersRecyclerViewAdapter.OnItemClickListener {

    private ActivityMovieDetailsBinding binding;
    private static final String EMPTY = " ";
    private MovieDetailsContract.Presenter presenter;
    private Movie movie;
    private List<Trailer> trailers = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private TrailersRecyclerViewAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_movie_details );

        presenter = new MovieDetailsPresenter( this );

        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            String transitionPosterName = b.getString( MainActivity.TRANSITION_POSTER_POSITION );
            String transitionTitleName = b.getString( MainActivity.TRANSITION_TITLE_POSITION );
            movie = b.getParcelable( MainActivity.BUNDLE_MOVIE );
            if ( movie == null ) {
                finishActivity();
            }
            binding.movie.image.setTransitionName( transitionPosterName );
            ImageHelper.getDrawableFromNetwork( binding.movie.image, movie.getPosterThumbnail() );

            binding.movie.title.setTransitionName( transitionTitleName );
            binding.movie.title.setText( movie.getOriginalTitle() );
            binding.movie.date.setText( movie.getReleaseDate() );
            binding.movie.overview.setText( movie.getPlotSynopsis() );
            binding.movie.rating.setGrade( Float.parseFloat( movie.getRating() ) );
            ImageHelper.getDrawableFromNetwork( binding.poster, movie.getBackdropImage() );

            adapter = new TrailersRecyclerViewAdapter( this, new ArrayList<>(), this );
            ItemMovieDetailsBinding itemMovieDetailsBinding = binding.movie;

            itemMovieDetailsBinding.recyclerView.setAdapter( adapter );
            itemMovieDetailsBinding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

            presenter.fetchReviews( movie.getId() );
            presenter.fetchTrailers( movie.getId() );

            binding.movie.reviews.setOnClickListener( (View.OnClickListener) v -> {
                Intent intent = new Intent( this, ReviewsActivity.class );
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList( "list", reviews );
                intent.putExtras( bundle );
                startActivity( intent );
            } );
        }

        setSupportActionBar( binding.toolbar );
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setDisplayShowHomeEnabled( true );
            actionBar.setTitle( EMPTY );
        }

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_movie_details, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == android.R.id.home ) {
            finishActivity();
        } else if ( item.getItemId() == R.id.favorite ) {
            toggleStar( item );
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void toggleStar( MenuItem item ) {
        if ( item.isChecked() ) {
            item.setIcon( android.R.drawable.btn_star_big_off );
            presenter.removeFromFavorites( movie );
        } else {
            item.setIcon( android.R.drawable.btn_star_big_on );
            presenter.addToFavorites( movie );
        }
        item.setChecked( !item.isChecked() );
    }

    @Override
    public void onVideoOpen( String url ) {
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        startActivity( intent );
    }

    @Override
    public void onReviewsFetched( ArrayList<Review> reviews ) {
        if ( !reviews.isEmpty() ) {
            binding.movie.reviews.setVisibility( View.VISIBLE );
        }
        this.reviews = reviews;
    }

    @Override
    public void onTrailersFetched( List<Trailer> trailers ) {
        this.trailers = trailers;
        adapter.set( trailers );
    }

    private void finishActivity() {
        supportFinishAfterTransition();
    }

    @Override
    public void onItemClick( int position ) {
        presenter.openVideo( trailers.get( position ) );
    }
}

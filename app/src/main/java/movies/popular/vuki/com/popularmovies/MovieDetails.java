package movies.popular.vuki.com.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import movies.popular.vuki.com.popularmovies.databinding.ActivityMovieDetailsBinding;
import movies.popular.vuki.com.popularmovies.helpers.ImageHelper;
import movies.popular.vuki.com.popularmovies.models.Movie;

public class MovieDetails extends AppCompatActivity {

    ActivityMovieDetailsBinding binding;
    private static final String EMPTY = " ";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_movie_details );

        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            String transitionPosterName = b.getString( MainActivity.TRANSITION_POSTER_POSITION );
            String transitionTitleName = b.getString( MainActivity.TRANSITION_TITLE_POSITION );
            Movie movie = b.getParcelable( MainActivity.BUNDLE_MOVIE );
            if ( movie != null ) {
                binding.image.setTransitionName( transitionPosterName );
                ImageHelper.getDrawableFromNetwork( binding.image, movie.getPosterThumbnail() );

                binding.title.setTransitionName( transitionTitleName );
                binding.title.setText( movie.getOriginalTitle() );
                binding.date.setText( movie.getReleaseDate() );
                binding.overview.setText( movie.getPlotSynopsis() );
                ImageHelper.getDrawableFromNetwork( binding.poster, movie.getBackdropImage() );
            }
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
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == android.R.id.home ) {
            finishActivity();
        }
        return super.onOptionsItemSelected( item );
    }

    private void finishActivity() {
        supportFinishAfterTransition();
    }
}

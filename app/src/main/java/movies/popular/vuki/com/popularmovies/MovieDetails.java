package movies.popular.vuki.com.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import movies.popular.vuki.com.popularmovies.databinding.ActivityMovieDetailsBinding;
import movies.popular.vuki.com.popularmovies.helpers.ImageHelper;
import movies.popular.vuki.com.popularmovies.models.Movie;

public class MovieDetails extends AppCompatActivity {

    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_movie_details );

        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            String transitionPosterName = b.getString( MainActivity.TRANSITION_POSTER_POSITION );
            String transitionTitleName = b.getString( MainActivity.TRANSITION_TITLE_POSITION );
            Movie movie = b.getParcelable( "movie" );
            if ( movie != null ) {
                ImageHelper.getDrawableFromNetwork( binding.poster, movie.getPosterThumbnail() );
                binding.title.setText( movie.getOriginalTitle() );

                binding.poster.setTransitionName( transitionPosterName );
                binding.title.setTransitionName( transitionTitleName );
            }
        }
    }

    @Override
    protected void onDestroy() {
        supportFinishAfterTransition();
        super.onDestroy();
    }
}

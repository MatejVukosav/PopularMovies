package movies.popular.vuki.com.popularmovies;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.popularmovies.databinding.ActivityMainBinding;
import movies.popular.vuki.com.popularmovies.databinding.ItemMovieBinding;
import movies.popular.vuki.com.popularmovies.models.Movie;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static int currentPosition;
    private MainActivityPresenter presenter;
    private ActivityMainBinding binding;
    private MovieAdapter movieAdapter;
    public static final String TRANSITION_POSTER_POSITION = "transitionPosterPosition";
    public static final String TRANSITION_TITLE_POSITION = "transitionTitlePosition";

    static final String DESC = ".desc";
    static final String highestRated = "vote_average" + DESC;
    static final String mostPopular = "popularity" + DESC;

    static final String BUNDLE_MOVIE = "movie" ;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS );
        // set an enter transition
        getWindow().setEnterTransition( new Explode() );
        // set an exit transition
        getWindow().setExitTransition( new Explode() );

        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );
        presenter = new MainActivityPresenter( this );

        movieAdapter = new MovieAdapter( new ArrayList<>(), presenter );

        if ( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            binding.recyclerView.setLayoutManager( new GridLayoutManager( this, 4 ) );
        } else {
            binding.recyclerView.setLayoutManager( new GridLayoutManager( this, 2 ) );
        }
        binding.recyclerView.setAdapter( movieAdapter );
        binding.recyclerView.setHasFixedSize( true );
        binding.recyclerView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );

        binding.toolbar.setTitle( R.string.main_activity_label );
        binding.toolbar.setOverflowIcon( ContextCompat.getDrawable( this, android.R.drawable.ic_menu_sort_by_size ) );
        setSupportActionBar( binding.toolbar );

        binding.recyclerView.addOnLayoutChangeListener(
                new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange( View view,
                                                int left,
                                                int top,
                                                int right,
                                                int bottom,
                                                int oldLeft,
                                                int oldTop,
                                                int oldRight,
                                                int oldBottom ) {
                        binding.recyclerView.removeOnLayoutChangeListener( this );
                        final RecyclerView.LayoutManager layoutManager =
                                binding.recyclerView.getLayoutManager();
                        View viewAtPosition =
                                layoutManager.findViewByPosition( MainActivity.currentPosition );
                        // Scroll to position if the view for the current position is null (not
                        // currently part of layout manager children), or it's not completely
                        // visible.
                        if ( viewAtPosition == null
                                || layoutManager.isViewPartiallyVisible( viewAtPosition, false, true ) ) {
                            binding.recyclerView.post( ()
                                    -> layoutManager.scrollToPosition( MainActivity.currentPosition ) );
                        }
                    }
                } );

        presenter.populateGrid( mostPopular );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.main_activity_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch ( item.getItemId() ) {
            case R.id.popular:
                binding.toolbar.setTitle( getResources().getString( R.string.most_popular ) );
                presenter.populateGrid( mostPopular );
                break;
            case R.id.top_rated:
                binding.toolbar.setTitle( getResources().getString( R.string.highest_rated ) );
                presenter.populateGrid( highestRated );
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onMovieDetailsClicked( Movie movie, int position ) {
        MainActivity.currentPosition = position;

        Bundle bundle = new Bundle();
        bundle.putString( TRANSITION_POSTER_POSITION, "poster" + position );
        bundle.putString( TRANSITION_TITLE_POSITION, "title" + position );
        bundle.putParcelable( BUNDLE_MOVIE, movie );

        ItemMovieBinding detailsBinding = DataBindingUtil.getBinding( binding.recyclerView.getLayoutManager().findViewByPosition( position ) );
        Pair<View, String> posterPair = Pair.create( detailsBinding.poster, "poster" + position );
        Pair<View, String> titlePair = Pair.create( detailsBinding.title, "title" + position );
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation( this, posterPair, titlePair );

        Intent intent = new Intent( MainActivity.this, MovieDetails.class );
        intent.putExtras( bundle );
        startActivity( intent, optionsCompat.toBundle() );
    }

    @Override
    public void onMoviesPopulate( List<Movie> movies ) {
        movieAdapter.setMovies( movies );
    }

}

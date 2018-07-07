package movies.popular.vuki.com.movies.main;

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
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.SortBy;
import movies.popular.vuki.com.movies.databinding.ActivityMainBinding;
import movies.popular.vuki.com.movies.databinding.ItemMovieBinding;
import movies.popular.vuki.com.movies.details.MovieDetails;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityContract.Presenter presenter;
    private ActivityMainBinding binding;
    private MovieAdapter movieAdapter;
    public static final String TRANSITION_POSTER_POSITION = "transitionPosterPosition";
    public static final String TRANSITION_TITLE_POSITION = "transitionTitlePosition";

    public static final String BUNDLE_MOVIE = "movie";
    public static final String TRANSITION_POSTER_ITEM = "poster";
    public static final String TRANSITION_TITLE_ITEM = "title";

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

        presenter.populateGrid( SortBy.mostPopular );
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
                presenter.populateGrid( SortBy.mostPopular );
                break;
            case R.id.top_rated:
                binding.toolbar.setTitle( getResources().getString( R.string.highest_rated ) );
                presenter.populateGrid( SortBy.highestRated );
                break;
            case R.id.favorites:
                binding.toolbar.setTitle( getResources().getString( R.string.favorites ) );
                presenter.populateGrid( SortBy.favorites );
                break;
        }
        return true;
    }

    @Override
    public void onMovieDetailsClicked( Movie movie, int position ) {
        Bundle bundle = new Bundle();
        bundle.putString( TRANSITION_POSTER_POSITION, TRANSITION_POSTER_ITEM + position );
        bundle.putString( TRANSITION_TITLE_POSITION, TRANSITION_TITLE_ITEM + position );
        bundle.putParcelable( BUNDLE_MOVIE, movie );

        ItemMovieBinding detailsBinding = DataBindingUtil.getBinding( binding.recyclerView.getLayoutManager().findViewByPosition( position ) );
        Pair<View, String> posterPair = Pair.create( detailsBinding.poster, TRANSITION_POSTER_ITEM + position );
        Pair<View, String> titlePair = Pair.create( detailsBinding.title, TRANSITION_TITLE_ITEM + position );
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

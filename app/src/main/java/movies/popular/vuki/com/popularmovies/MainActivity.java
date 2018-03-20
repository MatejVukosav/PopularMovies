package movies.popular.vuki.com.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.popularmovies.databinding.ActivityMainBinding;
import movies.popular.vuki.com.popularmovies.models.Movie;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter presenter;
    private ActivityMainBinding binding;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

        movieAdapter = new MovieAdapter( new ArrayList<Movie>() );

        binding.recyclerView.setLayoutManager( new GridLayoutManager( this, 2 ) );
        binding.recyclerView.setAdapter( movieAdapter );
        binding.recyclerView.setHasFixedSize( true );
        binding.recyclerView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );

        presenter = new MainActivityPresenter( this );
        presenter.populateGrid();
    }

    @Override
    public void onMovieDetailsClicked() {

    }

    @Override
    public void onSortClicked() {

    }

    @Override
    public void onMoviesPopulate( List<Movie> movies ) {
        movieAdapter.addMovies( movies );
    }
}

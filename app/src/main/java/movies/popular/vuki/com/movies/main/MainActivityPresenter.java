package movies.popular.vuki.com.movies.main;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.movies.OrderBy;
import movies.popular.vuki.com.movies.SortBy;
import movies.popular.vuki.com.movies.models.ApiMovie;
import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.network.ApiManager;
import movies.popular.vuki.com.movies.network.ErrorHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mvukosav
 */
public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = MainActivityPresenter.class.getCanonicalName();
    private final MainActivityContract.View view;

    MainActivityPresenter( MainActivityContract.View view ) {
        this.view = view;
    }

    @Override
    public void populateGrid( @SortBy String sortBy ) {
        Call<ApiMovie> apiMovieCall;
        if ( SortBy.highestRated.equals( sortBy ) ) {
            apiMovieCall = ApiManager.getInstance().getService().getTopRatedMovies();
        } else if ( SortBy.mostPopular.equals( sortBy ) ) {
            apiMovieCall = ApiManager.getInstance().getService().getPopularMovies();
        } else if ( SortBy.favorites.equals( sortBy ) ) {
            getFromDatabase();
            return;
        } else {
            apiMovieCall = ApiManager.getInstance().getService().discoverMovies( sortBy + OrderBy.DESC );
        }
        apiMovieCall.enqueue( new Callback<ApiMovie>()

        {
            @Override
            public void onResponse( @NonNull Call<ApiMovie> call, @NonNull Response<ApiMovie> response ) {
                if ( response.isSuccessful() ) {
                    ApiMovie body = response.body();
                    if ( body != null ) {
                        view.onMoviesPopulate( body.getMovies() );
                    }
                } else if ( response.errorBody() != null ) {
                    ErrorHandler.parseError( response.errorBody() );
                }
            }

            @Override
            public void onFailure( @NonNull Call<ApiMovie> call, @NonNull Throwable t ) {
                Log.e( TAG, t.getLocalizedMessage() );
            }
        } );

    }

    private void getFromDatabase() {
        List<Movie> favorites = new ArrayList<>();
        favorites.add( new Movie( 1, "mock",
                "mock",
                "mock",
                "mock",
                "mock",
                "mock",
                true,
                true
        ) );

        view.onMoviesPopulate( favorites );
    }

    @Override
    public void openMovieDetails( Movie movie, int position ) {
        view.onMovieDetailsClicked( movie, position );
    }
}

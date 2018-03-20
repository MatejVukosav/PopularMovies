package movies.popular.vuki.com.popularmovies;

import android.support.annotation.NonNull;
import android.util.Log;

import movies.popular.vuki.com.popularmovies.models.ApiMovie;
import movies.popular.vuki.com.popularmovies.network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mvukosav
 */
public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = MainActivityPresenter.class.getCanonicalName();
    private MainActivityContract.View view;

    public MainActivityPresenter( MainActivityContract.View view ) {
        this.view = view;
    }

    @Override
    public void populateGrid() {
        Call<ApiMovie> topRatedMovies = ApiManager.getInstance().getService().getTopRatedMovies();
        topRatedMovies.enqueue( new Callback<ApiMovie>() {
            @Override
            public void onResponse( @NonNull Call<ApiMovie> call, @NonNull Response<ApiMovie> response ) {
                ApiMovie body = response.body();
                if ( body != null ) {
                    view.onMoviesPopulate( body.getMovies() );
                }
            }

            @Override
            public void onFailure( @NonNull Call<ApiMovie> call, Throwable t ) {
                Log.e( TAG, t.getLocalizedMessage() );
            }
        } );

    }
}

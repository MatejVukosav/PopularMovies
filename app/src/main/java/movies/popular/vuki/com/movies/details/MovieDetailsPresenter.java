package movies.popular.vuki.com.movies.details;

import android.support.annotation.NonNull;

import movies.popular.vuki.com.movies.models.ApiReviews;
import movies.popular.vuki.com.movies.models.ApiTrailers;
import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.models.Trailer;
import movies.popular.vuki.com.movies.network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mvukosav
 */
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private MovieDetailsContract.View view;

    public MovieDetailsPresenter( MovieDetailsContract.View view ) {
        this.view = view;
    }

    @Override
    public void addToFavorites( Movie movie ) {

    }

    @Override
    public void removeFromFavorites( Movie movie ) {

    }

    @Override
    public void fetchReviews( int movieId ) {
        Call<ApiReviews> apiMovieCall = ApiManager.getInstance().getService().fetchMovieReviews( movieId );
        apiMovieCall.enqueue( new Callback<ApiReviews>() {
            @Override
            public void onResponse( @NonNull Call<ApiReviews> call, @NonNull Response<ApiReviews> response ) {
                if ( response.body() != null && response.body().getReviews() != null ) {
                    view.onReviewsFetched( response.body().getReviews() );
                }
            }

            @Override
            public void onFailure( @NonNull Call<ApiReviews> call, Throwable t ) {
                t.printStackTrace();
            }
        } );
    }

    @Override
    public void fetchTrailers( int movieId ) {
        Call<ApiTrailers> apiTrailersCall = ApiManager.getInstance().getService().fetchMovieTrailers( movieId );
        apiTrailersCall.enqueue( new Callback<ApiTrailers>() {
            @Override
            public void onResponse( @NonNull Call<ApiTrailers> call, @NonNull Response<ApiTrailers> response ) {
                if ( response.body() != null && response.body().getTrailers() != null ) {
                    view.onTrailersFetched( response.body().getTrailers() );
                }
            }

            @Override
            public void onFailure( @NonNull Call<ApiTrailers> call, Throwable t ) {
                t.printStackTrace();
            }
        } );
    }

    @Override
    public void openVideo( Trailer trailer ) {
        String youtubeBaseUrl = "https://www.youtube.com/watch?v=";
        view.onVideoOpen( youtubeBaseUrl + trailer.getKey() );
    }
}

package movies.popular.vuki.com.movies.details;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import movies.popular.vuki.com.movies.App;
import movies.popular.vuki.com.movies.database.repositories.FavoritesRepository;
import movies.popular.vuki.com.movies.mappers.FavoritesToMovieMapper;
import movies.popular.vuki.com.movies.models.ApiReviews;
import movies.popular.vuki.com.movies.models.ApiTrailers;
import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.models.Review;
import movies.popular.vuki.com.movies.models.Trailer;
import movies.popular.vuki.com.movies.network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mvukosav
 */
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, FavoritesRepository.AsyncResponse {

    private MovieDetailsContract.View view;
    private final FavoritesRepository favoritesRepository;
    private final FavoritesToMovieMapper favoritesMapper;

    public MovieDetailsPresenter( MovieDetailsContract.View view ) {
        this.view = view;
        this.favoritesRepository = new FavoritesRepository( App.getInstance() );
        this.favoritesMapper = new FavoritesToMovieMapper();
    }

    @Override
    public void addToFavorites( Movie movie ) {
        favoritesRepository.insert( favoritesMapper.mapToDatabase( movie ) );
    }

    @Override
    public void isFavorite( int id ) {
        favoritesRepository.findById( this, id );
    }

    @Override
    public void removeFromFavorites( Movie movie ) {
        favoritesRepository.delete( favoritesMapper.mapToDatabase( movie ) );
    }

    @Override
    public void fetchReviews( int movieId ) {
        Call<ApiReviews> apiMovieCall = ApiManager.getInstance().getService().fetchMovieReviews( movieId );
        apiMovieCall.enqueue( new Callback<ApiReviews>() {
            @Override
            public void onResponse( @NonNull Call<ApiReviews> call, @NonNull Response<ApiReviews> response ) {
                if ( response.body() != null && response.body().getReviews() != null ) {
                    view.onReviewsFetched( (ArrayList<Review>) response.body().getReviews() );
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

    @Override
    public void findByIdResult( boolean isFavorite ) {
        view.onFavoriteListener( isFavorite );
    }
}

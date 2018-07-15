package movies.popular.vuki.com.movies.details;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.models.Review;
import movies.popular.vuki.com.movies.models.Trailer;

/**
 * Created by mvukosav
 */
public interface MovieDetailsContract {

    interface View {
        void toggleStar( MenuItem item );

        void onVideoOpen( String url );

        void onReviewsFetched( ArrayList<Review> reviews );

        void onTrailersFetched( List<Trailer> trailers );

        void onFavoriteListener( boolean isFavorite );

    }

    interface Presenter {
        void addToFavorites( Movie movie );

        void removeFromFavorites( Movie movie );

        void fetchReviews( int movieId );

        void fetchTrailers( int movieId );

        void openVideo( Trailer trailer );

        void isFavorite( int id );

    }
}

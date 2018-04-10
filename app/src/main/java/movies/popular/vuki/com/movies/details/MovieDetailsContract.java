package movies.popular.vuki.com.movies.details;

import android.view.MenuItem;

import movies.popular.vuki.com.movies.models.Movie;

/**
 * Created by mvukosav
 */
public interface MovieDetailsContract {

    interface View{
        void toggleStar( MenuItem item);
    }

    interface Presenter{
        void addToFavorites(Movie movie);
        void removeFromFavorites(Movie movie);
    }
}

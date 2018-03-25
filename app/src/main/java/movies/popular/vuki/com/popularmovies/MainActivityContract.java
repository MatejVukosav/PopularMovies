package movies.popular.vuki.com.popularmovies;

import java.util.List;

import movies.popular.vuki.com.popularmovies.models.Movie;

/**
 * Created by mvukosav
 */
public interface MainActivityContract {

    interface View {
        void onMovieDetailsClicked( Movie movie, int position );

        void onMoviesPopulate( List<Movie> movies );
    }

    interface Presenter {

        void populateGrid(String sortBy);

        void openMovieDetails( Movie movie,int position );

    }
}

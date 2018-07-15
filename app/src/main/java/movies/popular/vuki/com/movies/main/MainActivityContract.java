package movies.popular.vuki.com.movies.main;

import java.util.List;

import movies.popular.vuki.com.movies.models.Movie;

/**
 * Created by mvukosav
 */
public interface MainActivityContract {

    interface View {
        void onMovieDetailsClicked( Movie movie, int position );

        void onMoviesPopulate( List<Movie> movies );

    }

    interface Presenter {

        void populateGrid( String sortBy );

        void openMovieDetails( Movie movie, int position );

        void clean();

    }
}

package movies.popular.vuki.com.movies.details;

import movies.popular.vuki.com.movies.models.Movie;

/**
 * Created by mvukosav
 */
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private MovieDetailsContract.View view;

    public MovieDetailsPresenter( MovieDetailsContract.View view ) {
        this.view = view;
    }

    @Override
    public void addToFavorites(Movie movie) {

    }

    @Override
    public void removeFromFavorites(Movie movie) {

    }
}

package movies.popular.vuki.com.movies.details;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;
import movies.popular.vuki.com.movies.database.repositories.FavoritesRepository;
import movies.popular.vuki.com.movies.mappers.FavoritesToMovieMapper;
import movies.popular.vuki.com.movies.models.Movie;

/**
 * Created by mvukosav
 */
public class MovieDetailsViewModel extends AndroidViewModel {

    private final FavoritesRepository favoritesRepository;
    private final LiveData<List<FavoritesEntity>> favorites;
    private final FavoritesToMovieMapper favoritesMapper = new FavoritesToMovieMapper();

    public MovieDetailsViewModel( @NonNull Application application ) {
        super( application );

        this.favoritesRepository = new FavoritesRepository( application );
        this.favorites = favoritesRepository.getFavorites();
    }

    public void insert( Movie movie ) {
        favoritesRepository.insert( favoritesMapper.mapToDatabase( movie ) );
    }

    public void delete( Movie movie ) {
        favoritesRepository.delete( favoritesMapper.mapToDatabase( movie ) );
    }

}

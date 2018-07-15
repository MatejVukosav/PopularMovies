package movies.popular.vuki.com.movies.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;
import movies.popular.vuki.com.movies.database.repositories.FavoritesRepository;

/**
 * Created by mvukosav
 */
public class MainActivityViewModel extends AndroidViewModel {

    private final FavoritesRepository favoritesRepository;
    private final LiveData<List<FavoritesEntity>> favorites;

    public MainActivityViewModel( @NonNull Application application ) {
        super( application );

        this.favoritesRepository = new FavoritesRepository( application );
        this.favorites = favoritesRepository.getFavorites();
    }

    public LiveData<List<FavoritesEntity>> getFavorites() {
        return favorites;
    }

}

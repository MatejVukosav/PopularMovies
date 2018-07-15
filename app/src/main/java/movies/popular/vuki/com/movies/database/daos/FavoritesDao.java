package movies.popular.vuki.com.movies.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;


/**
 * Created by mvukosav
 */
@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM movie_table")
    LiveData<List<FavoritesEntity>> getAllMovies();

    @Query("SELECT * FROM movie_table WHERE movie_id == :id")
    FavoritesEntity findById( int id );

    @Insert
    void insert( FavoritesEntity movie );

    @Delete
    void delete( FavoritesEntity movie );
}

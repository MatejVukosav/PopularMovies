package movies.popular.vuki.com.movies.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import movies.popular.vuki.com.movies.database.daos.FavoritesDao;
import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;

/**
 * Created by mvukosav
 */
@android.arch.persistence.room.Database(entities = { FavoritesEntity.class }, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract FavoritesDao favoritesDao();

    private static Database db;

    public static Database getDatabase( Context context ) {
        if ( db == null ) {
            synchronized ( Database.class ) {
                if ( db == null ) {
                    RoomDatabase.Builder<Database> databaseBuilder = Room.databaseBuilder( context.getApplicationContext(),
                            Database.class, "movies-database" );
                    db = databaseBuilder.build();
                }
            }
        }
        return db;
    }

}

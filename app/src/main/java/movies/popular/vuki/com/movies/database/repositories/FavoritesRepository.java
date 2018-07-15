package movies.popular.vuki.com.movies.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import movies.popular.vuki.com.movies.database.Database;
import movies.popular.vuki.com.movies.database.daos.FavoritesDao;
import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;

/**
 * Created by mvukosav
 */
public class FavoritesRepository {
    private FavoritesDao favoritesDao;
    private LiveData<List<FavoritesEntity>> favorites;

    public interface AsyncResponse {
        void findByIdResult( boolean isFavorite );
    }

    public FavoritesRepository( Application application ) {
        Database db = Database.getDatabase( application );
        favoritesDao = db.favoritesDao();
        favorites = db.favoritesDao().getAllMovies();
    }

    public LiveData<List<FavoritesEntity>> getFavorites() {
        return favorites;
    }

    public void insert( FavoritesEntity movie ) {
        new insertAsyncTask( favoritesDao ).execute( movie );
    }

    public void findById( AsyncResponse asyncResponse, int id ) {
        new findByIdAsyncTask( favoritesDao, asyncResponse ).execute( id );
    }

    public void delete( FavoritesEntity movie ) {
        new deleteAsyncTask( favoritesDao ).execute( movie );
    }

    private static class findByIdAsyncTask extends AsyncTask<Integer, Void, FavoritesEntity> {
        private FavoritesDao mAsyncTaskDao;
        private AsyncResponse asyncResponse;

        findByIdAsyncTask( FavoritesDao dao, AsyncResponse callback ) {
            mAsyncTaskDao = dao;
            asyncResponse = callback;
        }

        @Override
        protected FavoritesEntity doInBackground( final Integer... params ) {
            return mAsyncTaskDao.findById( params[0] );
        }

        @Override
        protected void onPostExecute( FavoritesEntity favoritesEntity ) {
            super.onPostExecute( favoritesEntity );
            asyncResponse.findByIdResult( favoritesEntity != null );
        }
    }

    private static class insertAsyncTask extends AsyncTask<FavoritesEntity, Void, Void> {
        private FavoritesDao mAsyncTaskDao;

        insertAsyncTask( FavoritesDao dao ) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground( final FavoritesEntity... params ) {
            mAsyncTaskDao.insert( params[0] );
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<FavoritesEntity, Void, Void> {
        private FavoritesDao mAsyncTaskDao;

        deleteAsyncTask( FavoritesDao dao ) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground( final FavoritesEntity... params ) {
            mAsyncTaskDao.delete( params[0] );
            return null;
        }
    }
}

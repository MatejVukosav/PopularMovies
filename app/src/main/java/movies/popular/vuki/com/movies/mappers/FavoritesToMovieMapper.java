package movies.popular.vuki.com.movies.mappers;

import java.util.ArrayList;
import java.util.List;

import movies.popular.vuki.com.movies.database.entities.FavoritesEntity;
import movies.popular.vuki.com.movies.models.Movie;

/**
 * Created by mvukosav
 */
public class FavoritesToMovieMapper {

    public List<Movie> mapToApp( List<FavoritesEntity> entities ) {
        List<Movie> movies = new ArrayList<>();
        for ( FavoritesEntity entity : entities ) {
            movies.add( mapToApp( entity ) );
        }
        return movies;
    }

    public Movie mapToApp( FavoritesEntity favoritesEntity ) {
        return new Movie( favoritesEntity.getMovieId(),
                favoritesEntity.getTitle(),
                favoritesEntity.getPosterThumbnail(),
                favoritesEntity.getPlotSynopsis(),
                favoritesEntity.getRating(),
                favoritesEntity.getReleaseDate(),
                favoritesEntity.getBackdropImage(),
                favoritesEntity.isAdult(),
                favoritesEntity.isVideo() );
    }

    public FavoritesEntity mapToDatabase( Movie movie ) {
        return new FavoritesEntity( movie.getId(),
                movie.getOriginalTitle(),
                movie.getPosterThumbnail(),
                movie.getPlotSynopsis(),
                movie.getRating(),
                movie.getReleaseDate(),
                movie.getBackdropImage(),
                movie.isAdult(),
                movie.isVideo() );
    }
}

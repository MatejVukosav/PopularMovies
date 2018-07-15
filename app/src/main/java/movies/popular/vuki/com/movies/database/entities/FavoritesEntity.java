package movies.popular.vuki.com.movies.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mvukosav
 * This is an annotated class that describes a database table
 */
@Entity(tableName = "movie_table")
public class FavoritesEntity {
//    private int id;

    @PrimaryKey()
    @ColumnInfo(name = "movie_id")
    private int movieId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "poster_path")
    private String posterThumbnail;
    @ColumnInfo(name = "overview")
    private String plotSynopsis;
    @ColumnInfo(name = "vote_average")
    private String rating;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @ColumnInfo(name = "backdrop_path")
    private String backdropImage;
    @ColumnInfo(name = "adult")
    private boolean adult;
    @ColumnInfo(name = "video")
    private boolean video;

    public FavoritesEntity( @NonNull int movieId,
                            @NonNull String title,
                            @NonNull String posterThumbnail,
                            @NonNull String plotSynopsis,
                            @NonNull String rating,
                            @NonNull String releaseDate,
                            @NonNull String backdropImage,
                            @NonNull boolean adult,
                            @NonNull boolean video ) {
        this.movieId = movieId;
        this.title = title;
        this.posterThumbnail = posterThumbnail;
        this.plotSynopsis = plotSynopsis;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.backdropImage = backdropImage;
        this.adult = adult;
        this.video = video;
    }

    public void setMovieId( int movieId ) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId( int id ) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
    }

    public void setPosterThumbnail( String posterThumbnail ) {
        this.posterThumbnail = posterThumbnail;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis( String plotSynopsis ) {
        this.plotSynopsis = plotSynopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating( String rating ) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate( String releaseDate ) {
        this.releaseDate = releaseDate;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage( String backdropImage ) {
        this.backdropImage = backdropImage;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult( boolean adult ) {
        this.adult = adult;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo( boolean video ) {
        this.video = video;
    }
}

package movies.popular.vuki.com.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * Created by mvukosav
 */
@SuppressWarnings("unused")
public class Movie implements Parcelable {

    @Json(name = "id")
    private final int id;
    @Json(name = "title")
    private final String originalTitle;
    @Json(name = "poster_path")
    private final String posterThumbnail;
    @Json(name = "overview")
    private final String plotSynopsis;
    @Json(name = "vote_average")
    private final String rating;
    @Json(name = "release_date")
    private final String releaseDate;
    @Json(name = "backdrop_path")
    private final String backdropImage;
    @Json(name = "adult")
    private final boolean adult;
    @Json(name = "video")
    private final boolean video;

    public Movie( int id,
                  String originalTitle,
                  String posterThumbnail,
                  String plotSynopsis,
                  String rating,
                  String releaseDate,
                  String backdropImage,
                  boolean adult,
                  boolean video ) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterThumbnail = posterThumbnail;
        this.plotSynopsis = plotSynopsis;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.backdropImage = backdropImage;
        this.adult = adult;
        this.video = video;
    }

    private Movie( Parcel in ) {
        id = in.readInt();
        originalTitle = in.readString();
        posterThumbnail = in.readString();
        plotSynopsis = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
        backdropImage = in.readString();
        adult = in.readByte() != 0;
        video = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel( Parcel in ) {
            return new Movie( in );
        }

        @Override
        public Movie[] newArray( int size ) {
            return new Movie[size];
        }
    };

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel parcel, int i ) {
        parcel.writeInt( id );
        parcel.writeString( originalTitle );
        parcel.writeString( posterThumbnail );
        parcel.writeString( plotSynopsis );
        parcel.writeString( rating );
        parcel.writeString( releaseDate );
        parcel.writeString( backdropImage );
        parcel.writeByte( (byte) ( adult ? 1 : 0 ) );
        parcel.writeByte( (byte) ( video ? 1 : 0 ) );
    }
}

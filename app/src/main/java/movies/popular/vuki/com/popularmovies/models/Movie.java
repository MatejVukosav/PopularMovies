package movies.popular.vuki.com.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import movies.popular.vuki.com.popularmovies.helpers.ImageHelper;

/**
 * Created by mvukosav
 */
public class Movie implements Parcelable {

    @Json(name = "id")
    int id;
    @Json(name = "title")
    private
    String originalTitle;
    @Json(name = "poster_path")
    private
    String posterThumbnail;
    @Json(name = "overview")
    private
    String plotSynopsis;
    @Json(name = "vote_average")
    private
    String rating;
    @Json(name = "release_date")
    private
    String releaseDate;
    @Json(name = "backdrop_path")
    String backdropImage;
    @Json(name = "adult")
    boolean adult;
    @Json(name = "video")
    boolean video;

    protected Movie( Parcel in ) {
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
        return ImageHelper.IMAGE_BASE_URL + ImageHelper.w185 + posterThumbnail;
    }

    public String getBackdropImage() {
        return ImageHelper.IMAGE_BASE_URL + ImageHelper.w500 + backdropImage;
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

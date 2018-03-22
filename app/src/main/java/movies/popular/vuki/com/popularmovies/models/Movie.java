package movies.popular.vuki.com.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import movies.popular.vuki.com.popularmovies.helpers.ImageHelper;

/**
 * Created by mvukosav
 */
public class Movie implements Parcelable {

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

    protected Movie( Parcel in ) {
        originalTitle = in.readString();
        posterThumbnail = in.readString();
        plotSynopsis = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
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

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel parcel, int i ) {
        parcel.writeString( originalTitle );
        parcel.writeString( posterThumbnail );
        parcel.writeString( plotSynopsis );
        parcel.writeString( rating );
        parcel.writeString( releaseDate );
    }
}

package movies.popular.vuki.com.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * Created by mvukosav
 */
public class Review implements Parcelable {
    @Json(name = "id")
    String id;
    @Json(name = "content")
    String content;
    @Json(name = "url")
    String url;
    @Json(name = "author")
    String author;

    protected Review( Parcel in ) {
        id = in.readString();
        content = in.readString();
        url = in.readString();
        author = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel( Parcel in ) {
            return new Review( in );
        }

        @Override
        public Review[] newArray( int size ) {
            return new Review[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeString( id );
        dest.writeString( content );
        dest.writeString( url );
        dest.writeString( author );
    }
}

package movies.popular.vuki.com.popularmovies.models;

import com.squareup.moshi.Json;

/**
 * Created by mvukosav
 */
public class Movie {

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

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
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
}

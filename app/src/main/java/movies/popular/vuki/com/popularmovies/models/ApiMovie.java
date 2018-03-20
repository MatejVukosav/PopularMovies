package movies.popular.vuki.com.popularmovies.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by mvukosav
 */
public class ApiMovie {

    @Json(name = "results")
    private
    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}

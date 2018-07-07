package movies.popular.vuki.com.movies.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by mvukosav
 */
public class ApiTrailers {
    @Json(name = "results")
    List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }
}

package movies.popular.vuki.com.movies.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by mvukosav
 */
public class ApiReviews {
    @Json(name = "results")
    List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }
}

package movies.popular.vuki.com.movies.models;

import com.squareup.moshi.Json;

/**
 * Created by mvukosav
 */
public class Review {
    @Json(name = "id")
    String id;
    @Json(name = "content")
    String content;
    @Json(name = "url")
    String url;
    @Json(name = "author")
    String author;
}

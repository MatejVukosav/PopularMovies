package movies.popular.vuki.com.movies.models;

import com.squareup.moshi.Json;

import movies.popular.vuki.com.movies.network.Optional;

/**
 * Created by mvukosav
 */
public class ApiTrailers {

    @Json(name = "id")
    @Optional
    String id;
    @Optional
    @Json(name = "author")
    String author;
    @Optional
    @Json(name = "content")
    String content;
    @Optional
    @Json(name = "url")
    String url;
}

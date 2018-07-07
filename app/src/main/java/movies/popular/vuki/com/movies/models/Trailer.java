package movies.popular.vuki.com.movies.models;

import com.squareup.moshi.Json;

/**
 * Created by mvukosav
 */
public class Trailer {

    @Json(name = "id")
    String id;
    @Json(name = "name")
    String name;
    @Json(name = "site")
    String site;
    @Json(name = "type")
    String type;
    @Json(name = "size")
    int size;
    @Json(name = "key")
    String key;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getKey() {
        return key;
    }
}

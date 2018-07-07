package movies.popular.vuki.com.movies;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mvukosav
 */
@StringDef({
        SortBy.highestRated,
        SortBy.mostPopular
})

@Retention(RetentionPolicy.RUNTIME)
public @interface SortBy {
    String highestRated = "vote_average";
    String mostPopular = "popularity";
    String favorites = "favorites";
}


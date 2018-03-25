package movies.popular.vuki.com.movies;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mvukosav
 */
@StringDef({
        OrderBy.ASC,
        OrderBy.DESC
})

@Retention(RetentionPolicy.RUNTIME)
public @interface OrderBy {
    String ASC = ".asc";
    String DESC = ".desc";
}

package movies.popular.vuki.com.popularmovies.helpers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by mvukosav
 */
public class ImageHelper {

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String w92 = "w92";
    public static final String w154 = "w154";
    public static final String w185 = "w185";
    public static final String w342 = "w342";
    public static final String w500 = "w500";
    public static final String w780 = "w780";

    public static void getDrawableFromNetwork( ImageView target, String url ) {
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled( true );
        picasso.load( url )
//                .resize( 100,160 )
//                .centerCrop()
                .placeholder( android.R.drawable.ic_menu_help )
                .error( android.R.drawable.ic_dialog_alert )
                .into( target );
    }
}

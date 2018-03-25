package movies.popular.vuki.com.movies.network;

import android.widget.Toast;

import java.io.IOException;

import movies.popular.vuki.com.movies.App;
import movies.popular.vuki.com.movies.R;
import movies.popular.vuki.com.movies.models.ApiError;
import okhttp3.ResponseBody;

/**
 * Created by mvukosav
 */
public class ErrorHandler {

    @SuppressWarnings("unused")
    private static final String TAG = ErrorHandler.class.getCanonicalName();

    public static void parseError( ResponseBody errorBody ) {
        if ( errorBody.source() != null ) {
            ApiError error = null;
            try {
                error = ApiManager.getMoshi().adapter( ApiError.class )
                        .fromJson( errorBody.source() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            Toast.makeText( App.getInstance(),
                    error != null ? error.getStatusMessage() : App.getInstance().getString( R.string.error_api_undefined ),
                    Toast.LENGTH_SHORT ).show();
        }
    }
}

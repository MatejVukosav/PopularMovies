package movies.popular.vuki.com.movies;

import android.app.Application;
import android.util.Log;

import com.amitshekhar.DebugDB;

/**
 * Created by mvukosav
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Log.d( "DB", DebugDB.getAddressLog() );
    }

    public static App getInstance() {
        if ( instance == null ) {
            instance = new App();
        }
        return instance;
    }

}

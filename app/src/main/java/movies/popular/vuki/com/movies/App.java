package movies.popular.vuki.com.movies;

import android.app.Application;

/**
 * Created by mvukosav
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        if ( instance == null ) {
            instance = new App();
        }
        return instance;
    }
}

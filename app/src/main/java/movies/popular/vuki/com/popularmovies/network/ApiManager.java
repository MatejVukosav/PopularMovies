package movies.popular.vuki.com.popularmovies.network;

import com.squareup.moshi.Moshi;

import movies.popular.vuki.com.popularmovies.network.logging.LoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by mvukosav
 */
public class ApiManager implements ApiManagerInterface {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static ApiManager apiManagerInstance;
    private static ApiManagerService apiManagerService;

    //singleton
    private ApiManager() {
    }

    public static synchronized ApiManager getInstance() {
        if ( apiManagerInstance == null ) {
            apiManagerInstance = new ApiManager();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor( new LoggingInterceptor( LoggingInterceptor.LogLevel.FULL ) )
                    .addInterceptor( new ApiKeyQueryInterceptor() )
                    .build();

            Moshi moshi = new Moshi.Builder()
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .addConverterFactory( MoshiConverterFactory.create( moshi ) )
                    .client( client )
                    .build();

            apiManagerService = retrofit.create( ApiManagerService.class );
        }
        return apiManagerInstance;
    }

    @Override
    public ApiManagerService getService() {
        return apiManagerService;
    }

}
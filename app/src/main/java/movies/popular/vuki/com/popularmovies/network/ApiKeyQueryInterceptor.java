package movies.popular.vuki.com.popularmovies.network;

import java.io.IOException;

import movies.popular.vuki.com.popularmovies.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mvukosav
 */
public class ApiKeyQueryInterceptor implements Interceptor {
    @Override
    public Response intercept( Chain chain ) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter( "api_key",  BuildConfig.MOVIE_DB_API_KEY )
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url( url );

        Request request = requestBuilder.build();
        return chain.proceed( request );
    }
}

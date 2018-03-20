package movies.popular.vuki.com.popularmovies.network.logging;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import movies.popular.vuki.com.popularmovies.BuildConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by mvukosav
 */
public  class LoggingInterceptor implements Interceptor {

    private static final String TAG = "OkHttp";

    public enum LogLevel {
        NONE,
        BASIC,
        HEADERS,
        FULL
    }

    private final LogLevel logLevel;

    public LoggingInterceptor( LogLevel logLevel ) {
        this.logLevel = logLevel;
    }

    @Override
    public Response intercept( @NonNull Chain chain ) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder().header( "Content-Type", "application/json" );
        request = requestBuilder.build();

        long startMs;
        startMs = SystemClock.currentThreadTimeMillis();
        if ( logLevel.ordinal() >= LogLevel.BASIC.ordinal() ) {
            logMessage( TAG, String.format( "---> %s %s", request.method(), request.url() ) );
        }
        if ( logLevel.ordinal() >= LogLevel.HEADERS.ordinal() ) {
            logMessage( TAG, prettyHeaders( request.headers() ) );
        }
        if ( logLevel.ordinal() >= LogLevel.FULL.ordinal() && request.body() != null ) {
            final Request copy = request.newBuilder().build();
            try ( Buffer buffer = new Buffer() ) {
                RequestBody body = copy.body();
                if ( body != null ) {
                    body.writeTo( buffer );
                }
                final String bodyStr = buffer.readUtf8();
                logMessage( TAG, bodyStr );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        Response response = chain.proceed( request );

        long tookMs = SystemClock.currentThreadTimeMillis() - startMs;
        if ( logLevel.ordinal() >= LogLevel.BASIC.ordinal() ) {
            logMessage( TAG, String.format( "<--- (%s) for %s %s in %sms",
                    response.code(), request.method(), response.request().url(), tookMs ) );
        }
        if ( logLevel.ordinal() >= LogLevel.HEADERS.ordinal() ) {
            logMessage( TAG, prettyHeaders( response.headers() ) );
        }
        if ( logLevel.ordinal() >= LogLevel.FULL.ordinal() && response.body() != null ) {
            final ResponseBody responseBody = response.body();
            final String responseBodyString = responseBody != null ? responseBody.string() : null;
            logMessage( TAG, responseBodyString );

            // response body was consumed, replace it with a copy
            assert responseBody != null;
            final ResponseBody bodyCopy = ResponseBody.create( responseBody.contentType(), responseBodyString );
            response = response.newBuilder().body( bodyCopy ).build();
        }

        return response;
    }

    private void logMessage( String tag, String msg ) {
        if ( BuildConfig.DEBUG ) {
            Log.d( tag, msg );
        }
    }

    private String prettyHeaders( Headers headers ) {
        if ( headers.size() == 0 ) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append( "  Headers:" );

        for ( int i = 0; i < headers.size(); i++ ) {
            builder.append( "\n    " ).append( headers.name( i ) ).append( ": " ).append( headers.value( i ) );
        }

        return builder.toString();
    }

}
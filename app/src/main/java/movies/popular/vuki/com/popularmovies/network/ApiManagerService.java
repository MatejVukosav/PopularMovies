package movies.popular.vuki.com.popularmovies.network;

import movies.popular.vuki.com.popularmovies.models.ApiMovie;
import movies.popular.vuki.com.popularmovies.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mvukosav
 */
public interface ApiManagerService {

    String apiVersion = "3";

    @GET(apiVersion + "/movie")
    Call<Movie> getMovieById( @Query("movieId") int movieId );

    @GET(apiVersion + "/discover/movie")
    Call<ApiMovie> getTopRatedMovies( @Query("sort_by")String sortBy );

}
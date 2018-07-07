package movies.popular.vuki.com.movies.network;

import movies.popular.vuki.com.movies.models.ApiReviews;
import movies.popular.vuki.com.movies.models.ApiTrailers;
import movies.popular.vuki.com.movies.models.Movie;
import movies.popular.vuki.com.movies.SortBy;
import movies.popular.vuki.com.movies.models.ApiMovie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mvukosav
 */
public interface ApiManagerService {

    String apiVersion = "3";

    @SuppressWarnings("unused")
    @GET(apiVersion + "/movie")
    Call<Movie> getMovieById( @Query("movieId") int movieId );

    @GET(apiVersion + "/discover/movie ")
    Call<ApiMovie> discoverMovies( @Query("sort_by") @SortBy String sortBy );

    @GET(apiVersion + "/movie/popular")
    Call<ApiMovie> getPopularMovies();

    @GET(apiVersion + "/movie/top_rated")
    Call<ApiMovie> getTopRatedMovies();

    @GET(apiVersion + "/movie/{id}/videos")
    Call<ApiTrailers> fetchMovieTrailers( @Path("id") int movieId );

    @GET(apiVersion + "/movie/{id}/reviews")
    Call<ApiReviews> fetchMovieReviews( @Path("id") int movieId );

}
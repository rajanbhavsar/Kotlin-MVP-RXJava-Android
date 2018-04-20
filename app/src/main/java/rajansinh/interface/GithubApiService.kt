package rajansinh.`interface`

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rajansinh.sttl.model.CustomResult
import rajansinh.sttl.model.Github
import rajansinh.sttl.model.MovieResponse
import rajansinh.sttl.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rajan.bhavsar on 3/21/2018.
 */
interface GithubApiService {

    @retrofit2.http.GET("search/users")
    fun search(@retrofit2.http.Query("q") query: String,
               @retrofit2.http.Query("page") page: Int = 1,
               @retrofit2.http.Query("per_page") perPage: Int = 20,
               @retrofit2.http.Query("q") user: String): io.reactivex.Observable<Result>

    @GET("users")
    fun FetchAllUsers(): Observable<CustomResult>


    @GET("/users/{login}")
    fun getAllUsers(): Observable<Github>

    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String): Observable<MovieResponse>

    @GET("search/movie")
    fun getMoviesBasedOnQuery(@Query("api_key") api_key: String, @Query("query") q: String): Observable<MovieResponse>


    /**
     * Companion object for the factory
     */
    companion object Factory {

        fun create(): GithubApiService {
            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .client(httpClient.build())
                    .build()

            return retrofit.create(GithubApiService::class.java);
        }


        fun createMoviesRetrofit(): GithubApiService {
            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("http://api.themoviedb.org/3/")
                    .client(httpClient.build())
                    .build()

            return retrofit.create(GithubApiService::class.java);
        }
    }
}
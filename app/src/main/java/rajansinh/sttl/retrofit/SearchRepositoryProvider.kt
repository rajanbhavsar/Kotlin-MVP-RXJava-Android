package rajansinh.sttl.retrofit

import rajansinh.`interface`.GithubApiService

/**
 * Created by rajan.bhavsar on 3/21/2018.
 */
object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.create())
    }

    fun provideMovieRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.createMoviesRetrofit())
    }

}
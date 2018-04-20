package rajansinh.sttl.retrofit

import io.reactivex.Observable
import rajansinh.`interface`.GithubApiService
import rajansinh.sttl.model.CustomResult
import rajansinh.sttl.model.Github
import rajansinh.sttl.model.MovieResponse
import rajansinh.sttl.model.Result

/**
 * Created by rajan.bhavsar on 3/21/2018.
 */
class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String, user: String, page: Int): io.reactivex.Observable<Result> {
        return apiService.search(query = "location:$location language:$language", page = page, perPage = 20, user = user)
    }

    fun FetchAllUsersList(): Observable<CustomResult> {
        return apiService.FetchAllUsers()
    }

    fun getAllUsersFromGithub(): Observable<Github> {
        return apiService.getAllUsers()
    }

    fun getAllDisplayMovies() : Observable<MovieResponse>{
        return apiService.getMovies("004cbaf19212094e32aa9ef6f6577f22")
    }



}
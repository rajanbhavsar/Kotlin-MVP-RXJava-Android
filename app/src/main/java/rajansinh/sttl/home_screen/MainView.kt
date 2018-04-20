package rajansinh.sttl.home_screen

import rajansinh.sttl.model.MovieResponse
import rajansinh.sttl.model.ResultData

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public interface MainView {

    fun ShowProgress()

    fun HideProgress()

    fun NavigateToOtherScreen()

    fun displayMovies(movieResponse: MovieResponse)

    fun displayMoviesOffline(offlinedata : MutableList<ResultData>)

    fun displayError(s: String)
}
package rajansinh.sttl.home_screen

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import rajansinh.MyApplication
import rajansinh.sttl.adapters.MoviesAdapter
import rajansinh.sttl.com.R
import rajansinh.sttl.com.databinding.ActivityMainBinding
import rajansinh.sttl.model.MovieResponse
import rajansinh.sttl.model.ResultData
import rajansinh.sttl.utils.Utils


/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public class MainScreen : AppCompatActivity(), MainView {

    lateinit var mainBinding: ActivityMainBinding
    public lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var mainscreenpresentator: MainScreenPresentator
    lateinit var movieadapter: MoviesAdapter
    var dataitems = mutableListOf<ResultData>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainscreenpresentator = MainScreenPresentatorImpl(this)
        linearLayoutManager = LinearLayoutManager(this)

        search_results_recycler_view.layoutManager = linearLayoutManager
        if (Utils.CheckConnectivity(this)!!) {
            mainscreenpresentator.getMovies()
        } else {
            mainscreenpresentator.getMoviesOffline()
        }


    }


    override fun displayMoviesOffline(offlinedata: MutableList<ResultData>) {
        dataitems = offlinedata;
        movieadapter = MoviesAdapter(offlinedata)
        search_results_recycler_view.adapter = movieadapter
    }


    override fun ShowProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun HideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun NavigateToOtherScreen() {
    }

    override fun displayMovies(movieResponse: MovieResponse) {
        if (movieResponse != null) {
            dataitems = movieResponse.results;
            MyApplication.database?.beginTransaction()
            MyApplication.database?.VideoListDao()?.deleteAll()
            MyApplication.database?.endTransaction()
            MyApplication.database?.VideoListDao()?.insertAll(dataitems)
            movieadapter = MoviesAdapter(dataitems)
            search_results_recycler_view.adapter = movieadapter
        }
    }

    override fun displayError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}
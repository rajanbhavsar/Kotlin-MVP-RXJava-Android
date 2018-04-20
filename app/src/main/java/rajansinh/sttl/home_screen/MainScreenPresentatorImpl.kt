package rajansinh.sttl.home_screen

import android.content.ContentValues.TAG
import android.support.annotation.NonNull
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import rajansinh.MyApplication
import rajansinh.sttl.model.MovieResponse
import rajansinh.sttl.model.ResultData
import rajansinh.sttl.retrofit.SearchRepository
import rajansinh.sttl.retrofit.SearchRepositoryProvider


/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public class MainScreenPresentatorImpl : MainScreenPresentator {

    var mainView: MainView?
    lateinit var repository: SearchRepository
    val mCompositeDisposable: CompositeDisposable

    public constructor(mainView: MainView) {
        this.mainView = mainView
        repository = SearchRepositoryProvider.provideMovieRepository()
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getMovies() {
        mCompositeDisposable.add(repository.getAllDisplayMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(getObserver()))
    }

    override fun getMoviesOffline() {
        Single.fromCallable {
            MyApplication.database?.VideoListDao()?.getAll()!!
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<Any> {
                    override fun accept(t: Any) {
                        Log.e("Data---",""+t);
                        mainView?.displayMoviesOffline(t as MutableList<ResultData>)
                    }

                }, Consumer<Throwable> { })
    }

    override fun onDestroy() {
        mainView = null
    }

    fun getObserver(): DisposableObserver<MovieResponse> {
        return object : DisposableObserver<MovieResponse>() {

            override fun onNext(@NonNull movieResponse: MovieResponse) {
                mainView?.displayMovies(movieResponse)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d(TAG, "Error" + e)
                e.printStackTrace()
                mainView?.displayError("Error fetching Movie Data")
            }

            override fun onComplete() {
                Log.d(TAG, "Completed")
                mainView?.HideProgress()
            }
        }
    }


}
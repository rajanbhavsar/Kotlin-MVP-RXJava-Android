package rajansinh.sttl.com

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import rajansinh.sttl.adapters.UserAdapter
import rajansinh.sttl.com.databinding.ActivityMainBinding
import rajansinh.sttl.model.Github
import rajansinh.sttl.model.User
import rajansinh.sttl.retrofit.SearchRepository
import rajansinh.sttl.retrofit.SearchRepositoryProvider
import java.util.function.Consumer
import android.icu.util.ULocale.getCountry
import io.reactivex.Observable
import rajansinh.MyApplication
import rajansinh.sttl.model.ResultData
import java.util.concurrent.Callable


class MainActivity : AppCompatActivity() {

    var mIsloading = false
    var mTotalSize = 0
    var searchedString: String = "all"
    var page: Int = 1
    lateinit var mainBinding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var repository: SearchRepository

    private val mCompositeDisposable = CompositeDisposable()
    lateinit var userAdapter: UserAdapter
    var dataitems = mutableListOf<User>()

    private var mIsFromLastItem: Boolean = false

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository = SearchRepositoryProvider.provideSearchRepository()
        linearLayoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(dataitems)
        search_results_recycler_view.layoutManager = linearLayoutManager
        swipe_refresh.setOnRefreshListener {
            if (swipe_refresh.isRefreshing) {
                swipe_refresh.isRefreshing = false
            }
            swipe_refresh.isRefreshing = true
            page = 1
            userAdapter.clearData()
            requestGeonames(searchedString)
        }
        search_results_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                search_results_recycler_view.isEnabled = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0
            }


            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (totalItemCount < mTotalSize && mTotalSize > 10) {
                    if (!mIsloading && totalItemCount - visibleItemCount <= firstVisibleItemPosition + 2) {
                        mIsFromLastItem = true
                        page++
                        requestGeonames(searchedString)
                    }
                }
            }
        })



        search_results_recycler_view.adapter = userAdapter
        requestGeonames("all")
        searchedString = "all"
        search_button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                handleSearchRequest()
            }
        })
    }

    private fun handleSearchRequest() {
        val query = search_edit_text.text.toString().trim({ it <= ' ' })
        if (TextUtils.isEmpty(query)) {
            search_edit_text.error = "Required"
        } else {
            userAdapter.clearData()
            requestGeonames(query)
            searchedString = query
            page = 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    fun requestGeonames(temp: String) {
        mIsloading = true
        mCompositeDisposable.add(
                repository.searchUsers("Lagos", "Java", temp, page)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->

                            mIsloading = false
                            swipe_refresh.isRefreshing = false
                            Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
                            mTotalSize = result.total_count
                            for (e in result.items) {
                                println(e.login)
                            }
                            userAdapter.chageData(result.items)
                        }, { error ->
                            mIsloading = false
                            swipe_refresh.isRefreshing = false
                            error.printStackTrace()
                            page--
                        })
        )
    }

    fun getAllUserFromGithubPlatform(){

        mIsloading = true
        mCompositeDisposable.add(repository.getAllUsersFromGithub().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()

        )
    }


}

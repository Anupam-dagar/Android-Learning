package com.example.tvfoodapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseFragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.tvfoodapp.App
import com.example.tvfoodapp.ui.Presenters.CardPresenter
import com.example.tvfoodapp.R
import com.example.tvfoodapp.model.API.TMDBApi
import com.example.tvfoodapp.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragment : BrowseFragment() {

    @Inject
    lateinit var tmdbApi: TMDBApi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity.applicationContext as App).applicationComponent.inject(this)
        setupUIElements()
        createMovieRow()
    }

    fun setupUIElements() {
        title = "Videos available"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(activity, R.color.fastlane_background)
        searchAffordanceColor = ContextCompat.getColor(activity, R.color.search_opaque)
    }

    @SuppressLint("CheckResult")
    private fun createMovieRow() {
        tmdbApi.getPopularMovies("c1faeeb494d1ad57b496cfdf60084cf3").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> Log.d("resp", "$response") },
                { error -> Log.d("resp", "$error") })
        val movieList = mutableListOf<Movie>()
        val movie = Movie(
            156.168,
            200,
            "https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.svg",
            1234,
            "hello",
            "anupam",
            12.34,
            "this is an overview",
            "12-34-5678"
        )
        movieList.add(movie)
        movieList.add(movie)
        movieList.add(movie)
        movieList.add(movie)

        val rowAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        for (i in 0..3) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)

            for (j in 0..3) {
                listRowAdapter.add(movieList[j])
            }

            val header = HeaderItem("$i")
            rowAdapter.add(ListRow(header, listRowAdapter))
        }

        adapter = rowAdapter
    }
}
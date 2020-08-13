package com.example.tvfoodapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.tvfoodapp.App
import com.example.tvfoodapp.ui.Presenters.CardPresenter
import com.example.tvfoodapp.R
import com.example.tvfoodapp.model.API.TMDBApi
import com.example.tvfoodapp.model.Movie
import com.example.tvfoodapp.ui.ViewModel.MainFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var tmdbApi: TMDBApi

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val API_KEY = "c1faeeb494d1ad57b496cfdf60084cf3"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity!!.applicationContext as App).applicationComponent.inject(this)
        setupUIElements()
        createMovieRow()
    }

    fun setupUIElements() {
        title = "Videos available"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(context!!, R.color.fastlane_background)
        searchAffordanceColor = ContextCompat.getColor(context!!, R.color.search_opaque)
    }

    @SuppressLint("CheckResult")
    private fun createMovieRow() {
        val viewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]
        val rowAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        adapter = rowAdapter

        tmdbApi.getPopularMovies(API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    viewModel.addMovies(response.results)
                    addMovieRow(viewModel.getMovies(), rowAdapter, cardPresenter, "Popular Movies")
                },
                { error -> Log.d("RequestError", "$error") })

        tmdbApi.getTopRatedMovies(API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                viewModel.addTopRatedMovies(response.results)
                addMovieRow(
                    viewModel.getTopRatedMovies(),
                    rowAdapter,
                    cardPresenter,
                    "Top Rated Movies"
                )
            }, { error -> Log.d("RequestError", "$error") }
            )
    }

    private fun addMovieRow(
        movies: List<Movie>,
        rowAdapter: ArrayObjectAdapter,
        cardPresenter: CardPresenter,
        header: String
    ) {
        val listRowAdapter = ArrayObjectAdapter(cardPresenter)

        for (movie in movies) {
            listRowAdapter.add(movie)
        }

        rowAdapter.add(ListRow(HeaderItem(header), listRowAdapter))
    }
}
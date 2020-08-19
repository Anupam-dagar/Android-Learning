package com.example.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.ViewModelProvider
import com.example.ui.data.api.TMDBApi
import com.example.ui.data.Database
import com.example.ui.data.entity.Movie
import com.example.base.utils.InjectUtils
import com.example.ui.R
import com.example.ui.di.components.DaggerUiComponent
import com.example.ui.di.modules.ApiModule
import com.example.ui.presenters.CardPresenter
import com.example.ui.viewmodel.MainFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var tmdbApi: TMDBApi

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val API_KEY = "c1faeeb494d1ad57b496cfdf60084cf3"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiComponent.builder()
            .apiModule(ApiModule(activity?.applicationContext!!))
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        setupUIElements()
        setupData()
    }

    private fun setupUIElements() {
        title = "Movies"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(context!!, R.color.default_background)
        searchAffordanceColor = ContextCompat.getColor(context!!, R.color.tertiary)
    }

    private fun setupData() {
        val viewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]
        viewModel.addMovies()
//        val rowAdapter = ArrayObjectAdapter(ListRowPresenter())
//        val cardPresenter = CardPresenter()
//        adapter = rowAdapter
//
//        tmdbApi.getPopularMovies(API_KEY).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { response ->
//                    viewModel.addMovies(response.results)
//                    addMovieRow(viewModel.getMovies(), rowAdapter, cardPresenter, "Popular Movies")
//                },
//                { error -> Log.d("RequestError", "$error") })
//
//        tmdbApi.getTopRatedMovies(API_KEY).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
//                viewModel.addTopRatedMovies(response.results)
//                addMovieRow(
//                    viewModel.getTopRatedMovies(),
//                    rowAdapter,
//                    cardPresenter,
//                    "Top Rated Movies"
//                )
//            }, { error -> Log.d("RequestError", "$error") }
//            )
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
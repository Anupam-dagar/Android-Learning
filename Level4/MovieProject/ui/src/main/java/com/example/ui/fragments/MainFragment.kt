package com.example.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.data.Database
import com.example.base.data.entity.Movie
import com.example.ui.data.api.TMDBApi
import com.example.base.utils.InjectUtils
import com.example.ui.R
import com.example.ui.di.components.DaggerUiComponent
import com.example.ui.di.modules.ApiModule
import com.example.uicommon.presenters.CardPresenter
import com.example.ui.viewmodel.MainFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.example.uidetails.activities.DetailActivity
import io.reactivex.Single

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var tmdbApi: TMDBApi

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: MainFragmentViewModel
    lateinit var cardPresenter: CardPresenter
    lateinit var rowAdapter: ArrayObjectAdapter

    private val API_KEY = "c1faeeb494d1ad57b496cfdf60084cf3"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiComponent.builder()
            .apiModule(ApiModule())
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        setupUIElements()
        setupData()

        onItemViewClickedListener = ItemViewClickedListener()
    }

    private fun setupUIElements() {
        title = "Movies"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(context!!, R.color.secondary)
        searchAffordanceColor = ContextCompat.getColor(context!!, R.color.tertiary)
    }

    @SuppressLint("CheckResult")
    private fun setupData() {
        viewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]
        rowAdapter = ArrayObjectAdapter(ListRowPresenter())
        cardPresenter = CardPresenter()
        adapter = rowAdapter

        tmdbApi.getPopularMovies(API_KEY).subscribeOn(Schedulers.io())
            .subscribe(
                { response ->
                    addMovieRow(response.results, rowAdapter, cardPresenter, "Popular Movies")
                },
                { error -> Log.d("RequestError", "$error") })

        tmdbApi.getTopRatedMovies(API_KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    addMovieRow(
                        response.results,
                        rowAdapter,
                        cardPresenter,
                        "Top Rated Movies"
                    )
                }, { error -> Log.d("RequestError", "$error") }
            )

        viewModel.getMovies().observe(this, Observer {
            addMovieRow(it, rowAdapter, cardPresenter, "Favourite Movies")
        })


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

    inner class ItemViewClickedListener: OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            Log.d("clicked", "Media Item clicked: $item")
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("movie", item as Movie)
            startActivity(intent)
        }

    }
}
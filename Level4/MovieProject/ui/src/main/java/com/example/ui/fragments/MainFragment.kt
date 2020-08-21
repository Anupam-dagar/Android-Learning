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
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.base.data.Database
import com.example.base.data.entity.Movie
import com.example.base.data.entity.MovieResponse
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
import io.reactivex.Observable
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
        onItemViewClickedListener = ItemViewClickedListener()
    }

    override fun onResume() {
        setupData()
        super.onResume()
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
        prepareEntranceTransition()
        viewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]
        rowAdapter = ArrayObjectAdapter(ListRowPresenter())
        cardPresenter = CardPresenter()
        adapter = rowAdapter

        val favouriteMoviesAdapter = ArrayObjectAdapter(cardPresenter)
        val popularMoviesAdapter = ArrayObjectAdapter(cardPresenter)
        val topRatedMoviesAdapter = ArrayObjectAdapter(cardPresenter)

        rowAdapter.add(ListRow(HeaderItem("Popular Movies"), popularMoviesAdapter))
        rowAdapter.add(ListRow(HeaderItem("Top Rated Movies"), topRatedMoviesAdapter))
        rowAdapter.add(ListRow(HeaderItem("Favourite Movies"), favouriteMoviesAdapter))

        val popularMoviesObservable = tmdbApi.getPopularMovies(API_KEY)
        val topRatedMoviesObservable = tmdbApi.getTopRatedMovies(API_KEY)

        viewModel.getMovies("popular").observe(this, Observer {
            Log.d("test", "dbpo")
            addMovieRow(it, popularMoviesAdapter)
        })

        viewModel.getMovies("toprated").observe(this, Observer {
            Log.d("test", "dbtp")
            addMovieRow(it, topRatedMoviesAdapter)
        })

        viewModel.getFavouriteMovies().observe(this, Observer {
            val movies = mutableListOf<Movie>()
            for (favmovie in it) {
                val movie = Movie(
                    favmovie.vote_count,
                    favmovie.popularity,
                    favmovie.poster_path,
                    favmovie.id,
                    favmovie.backdrop_path,
                    favmovie.title,
                    favmovie.vote_average,
                    favmovie.overview,
                    favmovie.release_date,
                    favmovie.movieType
                )
                movies.add(movie)
            }
            addMovieRow(movies, favouriteMoviesAdapter)
        })

        Observable.merge(
            popularMoviesObservable.subscribeOn(Schedulers.io()).filter {
                for (i in it.results.indices) {
                    it.results[i].movieType = "popular"
                }
                true
            },
            topRatedMoviesObservable.subscribeOn(Schedulers.io()).filter {
                for (i in it.results.indices) {
                    it.results[i].movieType = "toprated"
                }
                true
            })
            .subscribe({
                viewModel.addMovies(it.results)
            }, { e -> Log.d("Request Error", "$e") })


    }

    private fun addMovieRow(
        movies: List<Movie>,
        itemAdapter: ArrayObjectAdapter
    ) {
        itemAdapter.clear()
        for (movie in movies) {
            itemAdapter.add(movie)
        }
    }

    inner class ItemViewClickedListener : OnItemViewClickedListener {
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
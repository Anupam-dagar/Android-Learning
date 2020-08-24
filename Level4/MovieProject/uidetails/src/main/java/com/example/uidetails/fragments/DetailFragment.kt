package com.example.uidetails.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.data.Database
import com.example.base.data.entity.FavouriteMovie
import com.example.base.data.entity.Movie
import com.example.base.utils.InjectUtils
import com.example.uicommon.presenters.CardPresenter
import com.example.uidetails.R
import com.example.uidetails.activities.DetailActivity
import com.example.uidetails.presenters.DetailsPresenter
import com.example.uidetails.presenters.StringPresenter
import com.example.uidetails.di.components.DaggerUiDetailComponent
import com.example.uidetails.presenters.ActionsPresenter
import com.example.uidetails.viewmodel.DetailFragmentViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.ref.WeakReference
import javax.inject.Inject
import kotlin.reflect.typeOf

class DetailFragment : DetailsSupportFragment(), ActionsPresenter.OnButtonClickListener {
    private lateinit var rowsAdapter: ArrayObjectAdapter
    val backgroundController = DetailsSupportFragmentBackgroundController(this)

    val BASE_IMAGE_URL_BACKDROP = "https://image.tmdb.org/t/p/w1280"
    val BASE_IMAGE_URL_POSTER = "https://image.tmdb.org/t/p/w500"

    var movie: Movie? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var actionPresenter: ActionsPresenter
    lateinit var viewModel: DetailFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiDetailComponent.builder()
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        backgroundController.enableParallax()
        backgroundController.solidColor =
            ResourcesCompat.getColor(resources, R.color.tertiary, activity?.theme)
        viewModel = ViewModelProvider(this, factory)[DetailFragmentViewModel::class.java]

        onItemViewClickedListener = ItemViewClickedListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        movie = arguments?.getParcelable("movie")
        buildDetails()
        super.onActivityCreated(savedInstanceState)
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        getBitmapSingle(Picasso.get(), BASE_IMAGE_URL_BACKDROP + movie?.backdrop_path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ bitmap ->
                backgroundController.coverBitmap = bitmap
            }, Throwable::printStackTrace)
        Log.d("testfrag", "onstartcompleted")
        super.onStart()
    }

    fun getBitmapSingle(picasso: Picasso, url: String): Single<Bitmap> = Single.create {
        try {
            if (!it.isDisposed) {
                val bitmap: Bitmap = picasso.load(url).get()
                it.onSuccess(bitmap)
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    @SuppressLint("CheckResult")
    private fun buildDetails() {
        val selector = ClassPresenterSelector().apply {
            FullWidthDetailsOverviewRowPresenter(DetailsPresenter()).also {
                addClassPresenter(DetailsOverviewRow::class.java, it)
            }
            addClassPresenter(ListRow::class.java, ListRowPresenter())
        }

        rowsAdapter = ArrayObjectAdapter(selector)

        getFavouriteMovie(movie!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                setupDetailView(it)
                setupRelatedMovies()

                adapter = rowsAdapter
            }, Throwable::printStackTrace)
    }

    fun setupRelatedMovies() {
        val listRowAdapter = ArrayObjectAdapter(CardPresenter())

        viewModel.getFavouriteMovies().observe(this, Observer {
            listRowAdapter.clear()
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
                listRowAdapter.add(movie)
            }
        })

        val header = HeaderItem(0, "Favourite Movies")
        rowsAdapter.add(ListRow(header, listRowAdapter))
    }

    fun setupDetailView(addToFavourites: Boolean) {
        actionPresenter = ActionsPresenter(this@DetailFragment, addToFavourites)

        val detailsOverview = DetailsOverviewRow(movie).apply {
            getBitmapSingle(Picasso.get(), BASE_IMAGE_URL_POSTER + movie?.poster_path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ bitmap ->
                    imageDrawable = BitmapDrawable(resources, bitmap)
                }, Throwable::printStackTrace)

            actionsAdapter = ArrayObjectAdapter(actionPresenter).apply {
                Log.d("he", "$addToFavourites")
                if (addToFavourites) add("Add To Favourite") else add("Remove From Favourites")
            }
        }

        rowsAdapter.add(detailsOverview)
    }

    fun getFavouriteMovie(movie: Movie): Single<Boolean> = Single.create {
        try {
            if (!it.isDisposed) {
                val favMovie = FavouriteMovie(
                    movie.vote_count,
                    movie.popularity,
                    movie.poster_path,
                    movie.id,
                    movie.backdrop_path,
                    movie.title,
                    movie.vote_average,
                    movie.overview,
                    movie.release_date,
                    movie.movieType
                )
                val movieData: FavouriteMovie? = viewModel.getFavouriteMovie(favMovie.id)
                if (movieData == null) {
                    it.onSuccess(true)
                } else {
                    it.onSuccess(false)
                }
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    fun addMovieToRoom(movie: Movie): Single<Movie> = Single.create {
        try {
            if (!it.isDisposed) {
                val favMovie = FavouriteMovie(
                    movie.vote_count,
                    movie.popularity,
                    movie.poster_path,
                    movie.id,
                    movie.backdrop_path,
                    movie.title,
                    movie.vote_average,
                    movie.overview,
                    movie.release_date,
                    movie.movieType
                )
                viewModel.addFavouriteMovie(favMovie)
                it.onSuccess(movie)
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    fun removeMovieFromRoom(movie: Movie): Single<Movie> = Single.create {
        try {
            if (!it.isDisposed) {
                val favMovie = FavouriteMovie(
                    movie.vote_count,
                    movie.popularity,
                    movie.poster_path,
                    movie.id,
                    movie.backdrop_path,
                    movie.title,
                    movie.vote_average,
                    movie.overview,
                    movie.release_date,
                    movie.movieType
                )
                viewModel.deleteFavouriteMovie(favMovie)
                it.onSuccess(movie)
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    fun updateButtonText(button: Button, buttonTitle: String) {
        button.text = buttonTitle
    }

    @SuppressLint("CheckResult")
    override fun onButtonClick(button: Button) {
        if (actionPresenter.addToFavourites) {
            addMovieToRoom(movie!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    actionPresenter.addToFavourites = !actionPresenter.addToFavourites
                    updateButtonText(button, "Remove From Favourites")
                }, Throwable::printStackTrace)
        } else {
            removeMovieFromRoom(movie!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    actionPresenter.addToFavourites = !actionPresenter.addToFavourites
                    updateButtonText(button, "Add To Favourites")
                }, Throwable::printStackTrace)
        }
    }


    inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (itemViewHolder is ActionsPresenter.ViewHolder) {
                onButtonClick(itemViewHolder.button)
            } else {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("movie", item as Movie)
                startActivity(intent)
            }
        }

    }
}
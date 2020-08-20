package com.example.uidetails.fragments

import android.annotation.SuppressLint
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
import androidx.lifecycle.ViewModelProvider
import com.example.base.data.Database
import com.example.base.data.entity.Movie
import com.example.base.utils.InjectUtils
import com.example.uidetails.R
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
        movie = activity?.intent?.getParcelableExtra<Movie>("movie")
        backgroundController.enableParallax()
        backgroundController.solidColor =
            ResourcesCompat.getColor(resources, R.color.tertiary, activity?.theme)
        viewModel = ViewModelProvider(this, factory)[DetailFragmentViewModel::class.java]
        buildDetails()
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        getBitmapSingle(Picasso.get(), BASE_IMAGE_URL_BACKDROP + movie?.backdrop_path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ bitmap ->
                backgroundController.coverBitmap = bitmap
                // val drawable = BitmapDrawable(context, bitmap)
            }, Throwable::printStackTrace)
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

        actionPresenter = ActionsPresenter(this@DetailFragment)

        val detailsOverview = DetailsOverviewRow(movie).apply {
            getBitmapSingle(Picasso.get(), BASE_IMAGE_URL_POSTER + movie?.poster_path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ bitmap ->
                    imageDrawable = BitmapDrawable(resources, bitmap)
                }, Throwable::printStackTrace)

            actionsAdapter = ArrayObjectAdapter(actionPresenter).apply {
                add("Add To Favourite")
            }
        }
        rowsAdapter.add(detailsOverview)

        val listRowAdapter = ArrayObjectAdapter(StringPresenter()).apply {
            add("Media Item 1")
            add("Media Item 2")
            add("Media Item 3")
        }

        val header = HeaderItem(0, "Related Items")
        rowsAdapter.add(ListRow(header, listRowAdapter))

        adapter = rowsAdapter
    }

    fun addMovieToRoom(movie: Movie): Single<Movie> = Single.create {
        try {
            if (!it.isDisposed) {
                viewModel.addMovie(movie)
                it.onSuccess(movie)
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    fun removeMovieFromRoom(movie: Movie): Single<Movie> = Single.create {
        try {
            if (!it.isDisposed) {
                viewModel.deleteMovie(movie)
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
    override fun onButtonClick(addToFavourites: Boolean, button: Button) {
        if (addToFavourites) {
            addMovieToRoom(movie!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movie ->
                    actionPresenter.addToFavourites = !addToFavourites
                    updateButtonText(button, "Remove From Favourites")
                }, Throwable::printStackTrace)
        } else {
            removeMovieFromRoom(movie!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movie ->
                    actionPresenter.addToFavourites = !addToFavourites
                    updateButtonText(button, "Add To Favourites")
                }, Throwable::printStackTrace)
        }
    }
}
package com.example.tvfoodapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseFragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.tvfoodapp.ui.Presenters.CardPresenter
import com.example.tvfoodapp.R
import com.example.tvfoodapp.model.Movie

class MainFragment : BrowseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun createMovieRow() {
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
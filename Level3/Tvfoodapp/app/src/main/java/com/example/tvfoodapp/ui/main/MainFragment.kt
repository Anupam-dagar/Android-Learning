package com.example.tvfoodapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.tvfoodapp.Presenters.CardPresenter
import com.example.tvfoodapp.R
import com.example.tvfoodapp.model.Movie

class MainFragment : BrowseSupportFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

    private fun createMovieRow() {
        val movieList = mutableListOf<Movie>()
        val movie = Movie(
            156.168,
            200,
            "b5XfICAvUe8beWExBz97i0Qw4Qh.jpg",
            1234,
            "hello",
            "anupam",
            12.34,
            "this is an overview",
            "10-11-1998"
        )
        movieList.add(movie)
        movieList.add(movie)
        movieList.add(movie)
        movieList.add(movie)

        val rowAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        for (i in 0..4) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)

            for (j in 0..4) {
                listRowAdapter.add(movieList[j])
            }

            val header = HeaderItem(i.toLong(), "movie category")
            rowAdapter.add(ListRow(header, listRowAdapter))
        }

        val gridHeader = HeaderItem(4.toLong(), "Preferences")

        val gridPresenter = GridItemPresenter()
        val gridRowAdapter = ArrayObjectAdapter(gridPresenter)
        gridRowAdapter.add("GridView")
        gridRowAdapter.add("Error Fragment")
        gridRowAdapter.add("Personal Settings")

        rowAdapter.add(ListRow(gridHeader, gridRowAdapter))

        adapter = rowAdapter
    }

    private inner class GridItemPresenter: Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val view = TextView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(200, 200)
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.setBackgroundColor(ContextCompat.getColor(parent.context, R.color.default_background))
            view.setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {
            TODO("Not yet implemented")
        }

    }
}
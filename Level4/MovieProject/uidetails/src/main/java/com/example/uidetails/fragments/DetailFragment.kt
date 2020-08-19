package com.example.uidetails.fragments

import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.example.base.utils.InjectUtils
import com.example.uidetails.R
import com.example.uidetails.presenters.DetailsPresenter
import com.example.uidetails.presenters.StringPresenter
import com.example.uidetails.di.components.DaggerUiDetailComponent
import com.example.uidetails.di.modules.DatabaseModule

class DetailFragment: DetailsSupportFragment(){
    private lateinit var rowsAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("detail", "onCreate")
        super.onCreate(savedInstanceState)
        DaggerUiDetailComponent.builder()
            .databaseModule(DatabaseModule(activity?.applicationContext!!))
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        buildDetails()
    }

    private fun buildDetails() {
        val selector = ClassPresenterSelector().apply {
            // Attach your media item details presenter to the row presenter:
            FullWidthDetailsOverviewRowPresenter(DetailsPresenter()).also {
                addClassPresenter(DetailsOverviewRow::class.java, it)
            }
            addClassPresenter(ListRow::class.java, ListRowPresenter())
        }
        rowsAdapter = ArrayObjectAdapter(selector)

        val res = activity?.resources
        val detailsOverview = DetailsOverviewRow("Media Item Details").apply {

            // Add images and action buttons to the details view
            imageDrawable = ResourcesCompat.getDrawable(resources, R.drawable.city, context?.theme)
            addAction(Action(1, "Buy $9.99"))
            addAction(Action(2, "Rent $2.99"))
        }
        rowsAdapter.add(detailsOverview)

        // Add a Related items row
        val listRowAdapter = ArrayObjectAdapter(StringPresenter()).apply {
            add("Media Item 1")
            add("Media Item 2")
            add("Media Item 3")
        }
        val header = HeaderItem(0, "Related Items")
        rowsAdapter.add(ListRow(header, listRowAdapter))

        adapter = rowsAdapter
    }
}
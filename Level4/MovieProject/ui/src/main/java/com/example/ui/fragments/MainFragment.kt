package com.example.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import com.example.base.api.TMDBApi
import com.example.base.data.Database
import com.example.base.utils.InjectUtils
import com.example.ui.R
import com.example.ui.di.components.DaggerUiComponent
import javax.inject.Inject

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var tmdbApi: TMDBApi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiComponent.builder()
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        Log.d("hello", "$tmdbApi")
    }

    private fun setupUIElements() {
        title = "Movies"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(context!!, R.color.default_background)
        searchAffordanceColor = ContextCompat.getColor(context!!, R.color.tertiary)
    }
}
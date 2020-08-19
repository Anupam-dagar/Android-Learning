package com.example.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import com.example.base.IResourceProvider
import com.example.base.data.Database
import com.example.base.utils.InjectUtils
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var resourceProvider: IResourceProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        val database = resourceProvider.providesDatabase()
        val sdatabase = resourceProvider.providesDatabase()

        Log.d("hello", "$database")
        Log.d("hello", "$sdatabase")

    }
}
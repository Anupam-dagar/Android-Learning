package com.example.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import com.example.base.data.Database
import com.example.base.utils.InjectUtils
import com.example.ui.di.components.DaggerUiComponent
import javax.inject.Inject

class MainFragment : BrowseSupportFragment() {

    @Inject
    lateinit var database: Database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiComponent.builder()
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
        Log.d("hello", "$database")
    }
}
package com.example.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import com.example.base.utils.InjectUtils
import com.example.ui.di.components.DaggerUiComponent

class MainFragment : BrowseSupportFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerUiComponent.builder()
            .baseComponent(InjectUtils.provideBaseComponent(activity?.applicationContext!!))
            .build()
            .inject(this)
    }
}
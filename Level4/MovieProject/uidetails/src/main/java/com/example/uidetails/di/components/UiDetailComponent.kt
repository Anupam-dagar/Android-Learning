package com.example.uidetails.di.components

import androidx.lifecycle.ViewModelProvider
import com.example.base.di.components.BaseComponent
import com.example.uidetails.activities.DetailActivity
import com.example.uidetails.di.modules.UiDetailModule
import com.example.uidetails.di.modules.ViewModelModule
import com.example.uidetails.di.scopes.UiDetailScope
import com.example.uidetails.fragments.DetailFragment
import dagger.Component
import retrofit2.Retrofit

@UiDetailScope
@Component(modules = [UiDetailModule::class, ViewModelModule::class], dependencies = [BaseComponent::class])
interface UiDetailComponent {
    fun inject(detailFragment: DetailFragment)
    fun inject(detailActivity: DetailActivity)
    fun getViewModel(): ViewModelProvider.Factory
}
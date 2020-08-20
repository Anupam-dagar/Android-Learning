package com.example.ui.di.components

import androidx.lifecycle.ViewModelProvider
import com.example.base.di.components.BaseComponent
import com.example.ui.di.modules.ApiModule
import com.example.ui.di.modules.UiModule
import com.example.ui.di.scopes.UiScope
import com.example.ui.fragments.MainFragment
import dagger.Component
import retrofit2.Retrofit

@UiScope
@Component(modules = [UiModule::class, ApiModule::class], dependencies = [BaseComponent::class])
interface UiComponent {
    fun inject(mainFragment: MainFragment)
    fun getViewModel(): ViewModelProvider.Factory
    fun getRetrofit(): Retrofit
}
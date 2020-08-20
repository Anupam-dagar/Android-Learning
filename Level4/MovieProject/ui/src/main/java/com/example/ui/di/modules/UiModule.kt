package com.example.ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui.factory.MainFragmentViewModelFactory
import com.example.ui.viewmodel.MainFragmentViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class UiModule {
    @Binds
    abstract fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MainFragmentViewModelFactory): ViewModelProvider.Factory
}
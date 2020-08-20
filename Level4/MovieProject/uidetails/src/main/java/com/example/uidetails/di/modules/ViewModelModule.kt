package com.example.uidetails.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uidetails.factory.DetailFragmentViewModelFactory
import com.example.uidetails.viewmodel.DetailFragmentViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindDetailFragmentViewModel(detailFragmentViewModel: DetailFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DetailFragmentViewModelFactory): ViewModelProvider.Factory
}
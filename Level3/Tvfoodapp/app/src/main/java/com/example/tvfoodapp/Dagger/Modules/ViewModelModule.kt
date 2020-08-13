package com.example.tvfoodapp.Dagger.Modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvfoodapp.Dagger.Modules.annotation.ViewModelKey
import com.example.tvfoodapp.ui.Factory.MainFragmentViewModelFactory
import com.example.tvfoodapp.ui.ViewModel.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MainFragmentViewModelFactory): ViewModelProvider.Factory
}

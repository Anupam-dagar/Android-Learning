package com.example.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui.di.scopes.UiScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@UiScope
class MainFragmentViewModelFactory @Inject constructor(val viewModel: Provider<ViewModel>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModel.get() as T
}

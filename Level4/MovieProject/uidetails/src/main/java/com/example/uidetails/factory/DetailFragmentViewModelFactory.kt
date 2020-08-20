package com.example.uidetails.factory

import com.example.uidetails.di.scopes.UiDetailScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@UiDetailScope
class DetailFragmentViewModelFactory @Inject constructor(val viewModel: Provider<ViewModel>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModel.get() as T
}

package com.example.mvvmapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.data.ItemRepository

class MainViewModelFactory(private val itemRepository: ItemRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(itemRepository) as T
    }
}
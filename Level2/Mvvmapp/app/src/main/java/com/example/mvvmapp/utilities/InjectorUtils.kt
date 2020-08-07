package com.example.mvvmapp.utilities

import com.example.mvvmapp.data.Database
import com.example.mvvmapp.data.ItemRepository
import com.example.mvvmapp.ui.main.MainViewModelFactory

object InjectorUtils {
    fun provideMainViewModelFactory(): MainViewModelFactory {
        val itemRepository = ItemRepository.getInstance(Database.getInstance().itemDao)
        return MainViewModelFactory(itemRepository)
    }
}
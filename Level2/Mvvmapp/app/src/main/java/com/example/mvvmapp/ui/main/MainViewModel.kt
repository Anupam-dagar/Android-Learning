package com.example.mvvmapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.mvvmapp.data.Item
import com.example.mvvmapp.data.ItemRepository

class MainViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    fun getItems() = itemRepository.getItems()

    fun addItem(item: Item) = itemRepository.addItem(item)
}
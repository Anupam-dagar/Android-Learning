package com.example.mvvmapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ItemDAO {
    private val itemList = mutableListOf<Item>()
    private val items = MutableLiveData<List<Item>>()

    init {
        items.value = itemList
    }

    fun addItem(item: Item) {
        itemList.add(item)
        items.value = itemList
    }

    fun getItems(): LiveData<List<Item>> = items
}
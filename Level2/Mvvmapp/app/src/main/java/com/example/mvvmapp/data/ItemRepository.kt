package com.example.mvvmapp.data

class ItemRepository private constructor(private val itemsDao: ItemDAO) {

    fun addItem(item: Item) {
        itemsDao.addItem(item)
    }

    fun getItems() = itemsDao.getItems()

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(itemsDao: ItemDAO) = instance ?: synchronized(this) {
            instance ?: ItemRepository(itemsDao).also { instance = it }
        }
    }
}
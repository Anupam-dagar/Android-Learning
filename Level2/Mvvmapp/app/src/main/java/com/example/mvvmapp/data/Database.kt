package com.example.mvvmapp.data

class Database private constructor() {

    var itemDao = ItemDAO()

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also { instance = it }
        }
    }
}
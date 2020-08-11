package com.example.tvfoodapp.model

class Database private constructor() {

    var movieDao = MovieDAO()

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also { instance = it }
        }
    }
}

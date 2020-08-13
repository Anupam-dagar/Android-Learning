package com.example.tvfoodapp.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database private constructor() {

    @Inject
    lateinit var movieDao: MovieDAO

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also { instance = it }
        }
    }
}

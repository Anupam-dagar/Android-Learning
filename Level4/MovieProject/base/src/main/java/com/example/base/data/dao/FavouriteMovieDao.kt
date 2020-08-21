package com.example.base.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.base.data.entity.FavouriteMovie

@Dao
abstract class FavouriteMovieDao {
    @Query("SELECT * FROM FavouriteMovie WHERE movieType = :queryVal")
    abstract fun getFavouriteMovies(queryVal: String): LiveData<List<FavouriteMovie>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    abstract fun addFavouriteMovies(moviesData: List<FavouriteMovie>)

    @Insert
    abstract fun addFavouriteMovie(movie: FavouriteMovie)

    @Delete
    abstract fun deleteFavouriteMovie(movie: FavouriteMovie)

    @Query("SELECT * FROM FavouriteMovie WHERE id = :id")
    abstract fun getFavouriteMovie(id: Int): FavouriteMovie

    @Query("SELECT * FROM FavouriteMovie")
    abstract fun getFavouriteMovies(): LiveData<List<FavouriteMovie>>

    @Update
    abstract fun updateFavouriteMovie(movie: FavouriteMovie)
}
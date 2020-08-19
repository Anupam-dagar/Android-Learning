package com.example.base.di.modules

import android.app.Application
import androidx.room.Room
import com.example.base.api.TMDBApi
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BaseModule(val application: Application) {
    val BASE_URL = "https://api.themoviedb.org/3/"


    @Singleton
    @Provides
    fun providesDatabase(): Database {
        return Room.databaseBuilder(
            application.applicationContext,
            Database::class.java, "database-name"
        ).build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(database: Database): MovieDao = database.movieDao()

    @Provides
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun providesGsonFactory(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().setLenient().create())

    @Provides
    fun providesInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    fun providesRetrofitAdapter(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun providesTMDBApi(retrofit: Retrofit): TMDBApi {
        return retrofit.create(TMDBApi::class.java)
    }
}
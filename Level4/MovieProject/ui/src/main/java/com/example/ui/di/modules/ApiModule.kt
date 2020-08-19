package com.example.ui.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.ui.di.scopes.UiScope
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule(val application: Context) {
    @UiScope
    @Provides
    fun providesDatabase(): com.example.ui.data.Database {
        return Room.databaseBuilder(
            application,
            com.example.ui.data.Database::class.java, "database-name"
        ).build()
    }

    @UiScope
    @Provides
    fun providesMovieDao(database: com.example.ui.data.Database): com.example.ui.data.dao.MovieDao = database.movieDao()

    val BASE_URL = "https://api.themoviedb.org/3/"

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
    fun providesTMDBApi(retrofit: Retrofit): com.example.ui.data.api.TMDBApi {
        return retrofit.create(com.example.ui.data.api.TMDBApi::class.java)
    }
}
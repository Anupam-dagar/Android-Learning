package com.example.ui

import com.example.ui.di.modules.ApiModule
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestUiModule: ApiModule() {
    override fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient = mockk()

    override fun providesGsonFactory(): GsonConverterFactory = mockk()

    override fun providesInterceptor(): Interceptor = mockk()

    override fun providesRetrofitAdapter(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = mockk()

    override fun providesTMDBApi(retrofit: Retrofit): com.example.ui.data.api.TMDBApi = mockk()
}
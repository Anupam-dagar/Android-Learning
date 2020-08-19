package com.example.utils.di.modules

import android.app.Application
import android.content.Context
import com.example.base.IResourceProvider
import com.example.utils.di.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun bindResourceProvider(context: Context): IResourceProvider {
            return ResourceProviderImpl(context)
        }
    }

}
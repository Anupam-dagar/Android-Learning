package com.example.utils.di.components

import android.app.Application
import com.example.base.IResourceProvider
import com.example.utils.di.modules.UtilsModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UtilsModule::class])
interface UtilsComponent {

    fun getResourceProvider() : IResourceProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): UtilsComponent
    }
}
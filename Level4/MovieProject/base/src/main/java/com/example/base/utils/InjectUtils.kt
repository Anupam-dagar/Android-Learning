package com.example.base.utils

import android.content.Context
import com.example.base.di.components.BaseComponent
import com.example.base.di.components.BaseComponentProvider

object InjectUtils {
    fun provideBaseComponent(applicationContext: Context): BaseComponent {
        return if (applicationContext is BaseComponentProvider) {
            (applicationContext as BaseComponentProvider).provideBaseComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement BaseComponentProvider")
        }
    }
}
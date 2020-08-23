package com.example.base

import com.example.base.di.components.BaseComponent
import com.example.base.di.modules.BaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (BaseModule::class)
    ]
)
interface TestBaseComponent : BaseComponent {
    fun into(baseTest: BaseTest)
}
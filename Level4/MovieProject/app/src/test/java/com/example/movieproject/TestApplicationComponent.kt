package com.example.movieproject

import com.example.base.di.components.BaseComponent
import com.example.movieproject.Di.Components.ApplicationComponent
import com.example.movieproject.Di.Modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (ApplicationModule::class)
    ], dependencies = [BaseComponent::class]
)
interface TestApplicationComponent : ApplicationComponent {
    fun into(mainApplicationTest: MainApplicationTest)
}
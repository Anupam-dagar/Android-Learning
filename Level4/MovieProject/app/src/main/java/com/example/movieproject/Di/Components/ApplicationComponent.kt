package com.example.movieproject.Di.Components

import android.app.Application
import com.example.base.di.components.BaseComponent
import com.example.movieproject.Activities.MainActivity
import com.example.movieproject.App
import com.example.movieproject.Di.Modules.ApplicationModule
import com.example.movieproject.Di.scopes.AppScope
import com.example.utils.di.components.UtilsComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class], dependencies = [BaseComponent::class, UtilsComponent::class])
interface ApplicationComponent: AndroidInjector<App> {
     @Component.Builder
     interface Builder {
          @BindsInstance
          fun application(application: Application): ApplicationComponent.Builder

          fun baseComponent(baseComponent: BaseComponent): ApplicationComponent.Builder
          fun utilsComponent(utilsComponent: UtilsComponent): ApplicationComponent.Builder
          fun build(): ApplicationComponent
     }
}
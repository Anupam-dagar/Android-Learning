package com.example.ui.di.components

import com.example.base.di.components.BaseComponent
import com.example.ui.di.modules.UiModule
import com.example.ui.di.scopes.UiScope
import com.example.ui.fragments.MainFragment
import dagger.Component

@UiScope
@Component(modules = [UiModule::class], dependencies = [BaseComponent::class])
interface UiComponent {
    fun inject(mainFragment: MainFragment)
}
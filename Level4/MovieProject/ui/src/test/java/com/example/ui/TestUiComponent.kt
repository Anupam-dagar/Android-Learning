package com.example.ui

import com.example.base.di.components.BaseComponent
import com.example.ui.di.components.UiComponent
import com.example.ui.di.modules.ApiModule
import com.example.ui.di.modules.UiModule
import com.example.ui.di.scopes.UiScope
import dagger.Component

@UiScope
@Component(
    modules = [
        UiModule::class, ApiModule::class
    ], dependencies = [BaseComponent::class]
)
interface TestUiComponent : UiComponent {
    fun into(uiTest: UiTest)
}
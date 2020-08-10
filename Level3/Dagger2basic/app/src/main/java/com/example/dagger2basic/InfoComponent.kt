package com.example.dagger2basic

import dagger.Component

@Component
interface InfoComponent {
    fun inject(app: MainActivity)
}

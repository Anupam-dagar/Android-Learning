package com.example.movieproject

import android.app.Application
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainApplicationTest {

    @Before
    fun setup() {
        val component = DaggerTestApplicationComponent.builder().applicationModule(
            TestApplicationModule(
                Application()
            )
        ).build()
        component.into(this)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
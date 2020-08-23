package com.example.base

import android.app.Application
import com.example.base.data.Database
import com.example.base.di.components.BaseComponent
import com.example.base.utils.InjectUtils
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException
import javax.inject.Inject

class BaseTest {

    val application: Application = Application()

    @Inject
    lateinit var db: Database

    @Before
    fun setup() {
        val component = DaggerTestBaseComponent.builder().baseModule(
            TestBaseModule(
                application
            )
        ).build()
        component.into(this)
    }

    @Test
    fun testDatabaseIsInjected() {
        Assert.assertThat(db, instanceOf(Database::class.java))
    }
}
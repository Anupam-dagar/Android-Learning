package com.example.uidetails.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.base.utils.InjectUtils
import com.example.uidetails.R
import com.example.uidetails.di.components.DaggerUiDetailComponent
import com.example.uidetails.di.modules.DatabaseModule

class DetailActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        DaggerUiDetailComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)
    }
}
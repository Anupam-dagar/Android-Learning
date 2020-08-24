package com.example.uidetails.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.base.data.entity.Movie
import com.example.base.utils.InjectUtils
import com.example.uidetails.R
import com.example.uidetails.di.components.DaggerUiDetailComponent

class DetailActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val moviedata = intent.getParcelableExtra<Movie>("movie")
        Log.d("testact", "$moviedata")
        val bundle = Bundle()
        bundle.putParcelable("movie", moviedata)
        val fragment = supportFragmentManager.findFragmentById(R.id.detail_fragment)
        fragment?.arguments = bundle
        DaggerUiDetailComponent.builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)
    }
}
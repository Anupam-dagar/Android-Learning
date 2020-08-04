package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var cities = mutableListOf<String>()
    private var descriptions = mutableListOf<String>()
    lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cities = resources.getStringArray(R.array.cities).toMutableList()
        descriptions = resources.getStringArray(R.array.descriptions).toMutableList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = DataAdapter(cities, descriptions, R.drawable.city)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val swipeView = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        swipeView.setOnRefreshListener {
            Log.d("main", "listener")
            fetchNewData(0)
        }

        swipeView.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    fun fetchNewData(page: Int) {
        Log.d("main", "fetching")
        adapter.clear()
        adapter.addAll()

        swipeContainer.isRefreshing = false
    }
}
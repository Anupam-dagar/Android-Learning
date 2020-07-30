package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var cities = arrayOf<String>()
    private var descriptions = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cities = resources.getStringArray(R.array.cities)
        descriptions = resources.getStringArray(R.array.descriptions)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = DataAdapter(cities, descriptions, R.drawable.city)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
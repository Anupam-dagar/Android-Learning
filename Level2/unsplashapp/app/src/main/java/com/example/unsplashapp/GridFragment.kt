package com.example.unsplashapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridFragment : Fragment() {
    val imagearray = arrayListOf<String>("hello", "world","new","world")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_grid, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.imageGridRecyclerView)
        val adapter = RecyclerViewDataAdapter(imagearray)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(inflater.context,3)
        return view
    }
}